package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.ability.AbilityDamage;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.log.Combatlog;
import com.tacstargame.combat.log.CombatlogImpl;
import com.tacstargame.combat.log.StdCombatlogImpl;
import com.tacstargame.combat.statuseffect.StatusEffect;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.status.UnitStatus;
import java.util.ArrayList;
import java.util.List;

public class CombatImpl implements Combat {

    private List<Unit> playerGroup = new ArrayList<Unit>();
    private List<Unit> enemyGroup = new ArrayList<Unit>();

    private AbilityQueue abilityQueue = new AbilityQueueImpl();
    private CombatCalculator combatCalculator = new StdCombatCalculatorImpl();
    private Combatlog combatlog = new CombatlogImpl(new StdCombatlogImpl());

    private int roundCounter = 1;

    private boolean roundReady = false;
    private CombatResult combatResult;

    @Override
    public void calculateStep() {
        if (roundReady) {
            AbilityQueueEntry entry = getNextAbility();
            if (entry != null) {
                if (checkResource(entry) && checkUnitStatus(entry)) {
                    payResource(entry);
                    entry.ability.setRemainingCooldown(entry.ability.getMaxCooldown());
                    calculateTargets(entry);
                }
            } else {
                roundOver();
            }
        }
    }

    private void payResource(AbilityQueueEntry entry) {
        if (entry.ability.getAbilityResource().getClass().equals(entry.source.getPrimaryResource().getClass())) {
            entry.source.getPrimaryResource().increaseCurrentValue(-combatCalculator.calculateAbilityCost(entry.ability, entry.source, entry.target));
        } else if (entry.ability.getAbilityResource().getClass().equals(entry.source.getSecondaryResource().getClass())) {
            entry.source.getSecondaryResource().increaseCurrentValue(-combatCalculator.calculateAbilityCost(entry.ability, entry.source, entry.target));
        }
    }

    private void calculateTargets(AbilityQueueEntry entry) {
        switch (entry.ability.getAbilityTarget()) {
            case SINGLE:
                calculateSingleTarget(entry);
                break;
            case GROUP:
                List<Unit> targetGroup = getUnitGroup(entry.target);
                for (Unit unit : getUnitGroup(entry.target)) {
                    entry.target = unit;
                    calculateSingleTarget(entry);
                }
                break;
            case AOE:
                for (Unit unit : enemyGroup) {
                    entry.target = unit;
                    calculateSingleTarget(entry);
                }
                for (Unit unit : playerGroup) {
                    entry.target = unit;
                    calculateSingleTarget(entry);
                }
                break;
        }
    }
    
    private List<Unit> getUnitGroup(Unit unit) {
        if (playerGroup.contains(unit)) { return playerGroup; }
        if (enemyGroup.contains(unit)) { return enemyGroup; }
        return null;
    }

    private void calculateSingleTarget(AbilityQueueEntry entry) {
        if (calculateAbilityLanded(entry.source, entry.target, entry.ability)) {
            boolean reflected = false;
            boolean crit = false;
            boolean blocked = false;
            int amountResisted = 0;
            int amountBlocked = 0;

            if (combatCalculator.calculateAbilityReflect(entry.ability, entry.source, entry.target)) {
                entry.target = entry.source;
                reflected = true;
            }

            int value = 0;
            switch (entry.ability.getAbilityDamageType()) {
                case DAMAGE:
                    value = combatCalculator.calculateDamageAbilityValue(entry.ability, entry.source, entry.target);
                    break;
                case HEAL:
                    value = combatCalculator.calculateHealAbilityValue(entry.ability, entry.source, entry.target);
                    break;
                case STATUSEFFECT:
                    value = 0;
            }
            amountResisted = combatCalculator.calculateAbilityResistAmount(value, entry.ability, entry.source, entry.target);
            value = value - amountResisted;
            if (combatCalculator.calculateAbilityBlock(entry.ability, entry.source, entry.target)) {
                blocked = true;
                amountBlocked = combatCalculator.calculateBlockedAmount(value, entry.ability, entry.source, entry.target);
                value -= amountBlocked;
            }

            if (combatCalculator.calculateAbilityCrit(entry.ability, entry.source, entry.target)) {
                crit = true;
                value = combatCalculator.calculateCriticalDamageAmount(value, entry.ability, entry.source, entry.target);
            }

            entry.ability.execute(this, entry.source, entry.target);
            EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_SUCCESS, entry.source, entry.target, entry.ability, value, crit, reflected, blocked, amountBlocked, amountResisted);

            switch (entry.ability.getAbilityDamageType()) {
                case DAMAGE:
                    entry.target.getHealth().increaseCurrentValue(-value);
                    break;
                case HEAL:
                    entry.target.getHealth().increaseCurrentValue(value);
                    break;
                case STATUSEFFECT:
                    break;
            }
        }
    }

    private boolean calculateAbilityLanded(Unit source, Unit target, Ability ability) {
        if (ability.getAbilityDamage().equals(AbilityDamage.PHYSICAL)) {
            if (!combatCalculator.calculateAbilityHit(ability, source, target)) {
                if (!combatCalculator.calculateAbilityDodge(ability, source, target)) {
                    if (!combatCalculator.calculateAbilityParry(ability, source, target)) {
                        return true;
                    } else {
                        EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_PARRY, source, target, ability);
                        return false;
                    }
                } else {
                    EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_DODGE, source, target, ability);
                    return false;
                }
            } else {
                EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_MISS, source, target, ability);
                return false;
            }
        } else {
            if (!combatCalculator.calculateAbilityHit(ability, source, target)) {
                return true;
            } else {
                EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_MISS, source, target, ability);
                return false;
            }
        }
    }

    private boolean checkResource(AbilityQueueEntry entry) {
        if (combatCalculator.isAbilityCastable(entry.ability, entry.source)) {
            return true;
        }
        EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_RESOURCE_MISSING, entry.source, entry.target, entry.ability);
        return false;
    }

    private boolean checkUnitStatus(AbilityQueueEntry entry) {
        if (entry.source.getUnitStatusSet().isUnitStatus(UnitStatus.DEAD)) {
            EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_SOURCE_DEAD, entry.source, entry.target, entry.ability);
            return false;
        }

        if (entry.source.getUnitStatusSet().isUnitStatus(UnitStatus.INCAPACITATED)) {
            EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_ABILITY_CAST_FAILURE_INCAPACITATED, entry.source, entry.target, entry.ability);
            return false;
        }

        return true;
    }

    protected AbilityQueueEntry getNextAbility() {
        List<Unit> tmp = new ArrayList<Unit>();
        for (AbilityQueueEntry entry : abilityQueue.getAllEntries()) {
            tmp.add(entry.source);
        }
        Unit unit = combatCalculator.getFastestUnit(tmp);
        AbilityQueueEntry result = abilityQueue.getEntry(unit);
        abilityQueue.removeEntry(result);
        return result;
    }

    @Override
    public void roundReady() {
        roundReady = true;
        EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_STATE_CHANGED, roundReady, roundCounter);
    }

    @Override
    public List<Unit> getPlayerGroup() {
        return playerGroup;
    }

    @Override
    public List<Unit> getEnemyGroup() {
        return enemyGroup;
    }

    @Override
    public void addPlayer(Unit unit) {
        playerGroup.add(unit);
    }

    @Override
    public void addEnemy(Unit unit) {
        enemyGroup.add(unit);
    }

    @Override
    public int getCurrentRound() {
        return roundCounter;
    }

    @Override
    public void removePlayer(Unit unit) {
        playerGroup.remove(unit);
    }

    @Override
    public void removeEnemy(Unit unit) {
        enemyGroup.remove(unit);
    }

    @Override
    public AbilityQueue getAbilityQueue() {
        return abilityQueue;
    }

    @Override
    public void queuePlayerAbility(Unit source, Unit target, Ability ability) {
        if (!roundReady && ability.getRemainingCooldown() == 0) {
            abilityQueue.queuePlayerAbility(source, target, ability);
        }
    }

    @Override
    public void dequeuePlayerAbility(Unit unit) {
        abilityQueue.dequeuePlayerAbility(unit);
    }

    @Override
    public void setCombatCalculator(CombatCalculator combatCalculator) {
        this.combatCalculator = combatCalculator;
    }

    @Override
    public Combatlog getCombatlog() {
        return combatlog;
    }

    @Override
    public void updateBuffs() {
        updateBuffsOnGroup(playerGroup);
        updateBuffsOnGroup(enemyGroup);

    }

    public void updateBuffsOnGroup(List<Unit> units) {
        for (Unit unit : units) {
            for (StatusEffect effect : unit.getStatusEffectSet()) {
                effect.onTurnOver(unit);
            }
        }
    }

    public void checkBuffs(List<Unit> units) {
        for (Unit unit : units) {
            int i = 0;
            StatusEffect effect;
            while (i < unit.getStatusEffectSet().size()) {
                effect = unit.getStatusEffectSet().get(i);
                if (effect.getCurrentDuration() == 0) {
                    unit.getStatusEffectSet().fadeStatusEfect(effect);
                    i--;
                }
                i++;
            }
        }
    }

    @Override
    public void roundOver() {
        roundReady = false;
        roundCounter++;
        EventBusImpl.getInstance().fireEvent(EventBusEvent.COMBAT_STATE_CHANGED, roundReady, roundCounter);
    }

    @Override
    public boolean hasAbilityQueued(Unit unit) {
        return abilityQueue.hasAbilityQueued(unit);
    }

    @Override
    public boolean isCalculating() {
        return roundReady;
    }

}
