package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.ability.AbilityDamage;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.ChanceStat;
import com.tacstargame.combat.unit.stats.ModificatorStat;
import com.tacstargame.combat.unit.stats.ResistanceStat;
import com.tacstargame.combat.unit.status.UnitStatus;
import java.util.List;
import java.util.Random;

/**
 * Standard implementation of the CombatCalculator interface.
 *
 * @author Domenik
 */
public class StdCombatCalculatorImpl implements CombatCalculator {

    private double baseMissChance = 5.0;
    private double baseCritDamage = 2.0;
    private double baseCritHeal = 1.5;
    private double baseDodgeChance = 5.0;
    private double baseParryChance = 5.0;
    private double baseBlockChance = 5.0;
    private double baseResistChance = 5.0;
    private Random dice = new Random();

    @Override
    public int calculateDamageAbilityValue(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL EXTRA DMG DONE/TAKEN
        int value = 0;

        if (!target.getUnitStatusSet().isUnitStatus(UnitStatus.DEAD)) {
            value = ability.getAbilityValue(source);
            value += value * source.getStats().getStatValue(ModificatorStat.DAMAGEDONE);
            value -= value * target.getStats().getStatValue(ModificatorStat.DAMAGETAKEN);
        }

        return value;
    }

    @Override
    public int calculateHealAbilityValue(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL EXTRA HEALING DONE/TAKEN
        int value = 0;

        if (!target.getUnitStatusSet().isUnitStatus(UnitStatus.DEAD)) {
            value = ability.getAbilityValue(source);
            value += value * source.getStats().getStatValue(ModificatorStat.HEALINGDONE);
            value = value - calculateAbilityResistAmount(value, ability, source, target);
            value -= value * target.getStats().getStatValue(ModificatorStat.HEALINGTAKEN);
        }

        return value;
    }

    @Override
    public int calculateAbilityResistAmount(int amount, Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL REDUCTION
        double reduction = 0.0;
        switch (ability.getAbilityDamage()) {
            case PHYSICAL:
                reduction = target.getStats().getStatValue(ResistanceStat.ARMOR);
                break;
            case EARTH:
                reduction = target.getStats().getStatValue(ResistanceStat.EARTHRESISTANCE);
                break;
            case FIRE:
                reduction = target.getStats().getStatValue(ResistanceStat.FIRERESISTANCE);
                break;
            case ICE:
                reduction = target.getStats().getStatValue(ResistanceStat.ICERESISTANCE);
                break;
            case LIGHT:
                reduction = target.getStats().getStatValue(ResistanceStat.LIGHTRESISTANCE);
                break;
            case SHADOW:
                reduction = target.getStats().getStatValue(ResistanceStat.SHADOWRESISTANCE);
                break;
            case WIND:
                reduction = target.getStats().getStatValue(ResistanceStat.WINDRESISTANCE);
                break;
        }
        return (int) (amount * reduction);
    }

    @Override
    public boolean calculateAbilityHit(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL MISS CHANCE
        double misschance = baseMissChance + ability.getMissChance() - source.getStats().getStatValue(ChanceStat.ACCURACY) + target.getStats().getStatValue(ChanceStat.MISS);
        return rollDice(misschance);
    }

    @Override
    public boolean calculateAbilityTotalResist(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO RETHINK THIS
        double resistchance = baseResistChance;
        return rollDice(resistchance);
    }

    @Override
    public boolean calculateAbilityDodge(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL DODGE VALUE
        double dodgechance = baseDodgeChance + target.getStats().getStatValue(ChanceStat.DODGE);
        return rollDice(dodgechance);
    }

    @Override
    public boolean calculateAbilityParry(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL PARRY VALUE
        double parrychance = baseParryChance + target.getStats().getStatValue(ChanceStat.PARRY);
        return rollDice(parrychance);
    }

    @Override
    public boolean calculateAbilityBlock(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL BLOCK VALUE
        if (ability.getAbilityDamage().equals(AbilityDamage.PHYSICAL)) {
            double blockchance = baseBlockChance + target.getStats().getStatValue(ChanceStat.BLOCK);
            return rollDice(blockchance);
        }
        return false;
    }

    @Override
    public boolean calculateAbilityCrit(Ability ability, Unit source, Unit target) {
        // NOTE: NEED TO CALCULATE ACTUAL CRIT VALUES
        if (ability.canCrit()) {
            double critchance = source.getStats().getStatValue(ChanceStat.CRIT) + ability.getCritChance() - target.getStats().getStatValue(ResistanceStat.TOUGHNESS);
            return rollDice(critchance);
        }
        return false;
    }

    @Override
    public boolean calculateAbilityReflect(Ability ability, Unit source, Unit target) {
        return (ability.isReflectable()) && (target.getUnitStatusSet().isUnitStatus(UnitStatus.REFLECTING));
    }

    @Override
    public int calculateAbilityCost(Ability ability, Unit source, Unit target) {
        return ability.getResourceCost() - source.getStats().getStatValue(ModificatorStat.RESOURCECOSTS);
    }

    @Override
    public boolean isAbilityCastable(Ability ability, Unit source) {
        if (ability.getAbilityResource().getClass().equals(source.getPrimaryResource().getClass())) {
            if (source.getPrimaryResource().getCurrentValue() >= calculateAbilityCost(ability, source, source)) {
                return true;
            }
        } else if (ability.getAbilityResource().getClass().equals(source.getSecondaryResource().getClass())) {
            if (source.getSecondaryResource().getCurrentValue() >= calculateAbilityCost(ability, source, source)) {
                return true;
            }
        }
        return false;
    }

    private boolean rollDice(double value) {
        double result = dice.nextDouble() * 100;
        return !(result <= 100.0 - value);
    }

    @Override
    public double getBaseMissChance() {
        return baseMissChance;
    }

    @Override
    public double getBaseCritDamage() {
        return baseCritDamage;
    }

    @Override
    public double getBaseCritHeal() {
        return baseCritHeal;
    }

    @Override
    public double getBaseDodgeChance() {
        return baseDodgeChance;
    }

    @Override
    public double getBaseParryChance() {
        return baseParryChance;
    }

    @Override
    public double getBaseBlockChance() {
        return baseBlockChance;
    }

    @Override
    public double getBaseResistChance() {
        return baseResistChance;
    }

    @Override
    public Unit getFastestUnit(List<Unit> units) {
        Unit tmp = null;
        if (units.size() > 0) {
            tmp = units.get(0);
            for (Unit unit : units) {
                if (tmp.getStats().getStatValue(BaseStat.SPEED) < unit.getStats().getStatValue(BaseStat.SPEED)) {
                    tmp = unit;
                }
            }
        }
        return tmp;
    }

    @Override
    public int calculateBlockedAmount(int value, Ability ability, Unit source, Unit target) {
        if (ability.getAbilityDamage().equals(AbilityDamage.PHYSICAL)) {
            return (int) (value * 0.3);
        }
        return value;
    }

    @Override
    public int calculateCriticalDamageAmount(int value, Ability ability, Unit source, Unit target) {
        return (int) (value * getBaseCritDamage());
    }

    @Override
    public int calculateCriticalHealAmount(int value, Ability ability, Unit source, Unit target) {
        return (int) (value * getBaseCritHeal());
    }

}
