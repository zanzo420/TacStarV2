package com.tacstargame.combat.statuseffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tacstargame.combat.unit.Unit;

public class StatusEffectSetImpl implements StatusEffectSet {

    private List<StatusEffect> statusEffects = new ArrayList<StatusEffect>();
    private Unit unit;

    public StatusEffectSetImpl(Unit unit) {
        this.unit = unit;
    }

    @Override
    public void addStatusEffect(Unit source, StatusEffect statusEffect) {
        statusEffects.add(statusEffect);
        statusEffect.onApply(source, unit);
    }

    @Override
    public StatusEffect removeStatusEffect(StatusEffect statusEffect) {
        statusEffects.remove(statusEffect);
        statusEffect.onRemove(unit);
        return statusEffect;
    }

    @Override
    public StatusEffect dispellStatusEffect(StatusEffect statusEffect) {
        statusEffects.remove(statusEffect);
        statusEffect.onDispell(unit);
        return statusEffect;
    }

    @Override
    public StatusEffect fadeStatusEfect(StatusEffect statusEffect) {
        statusEffects.remove(statusEffect);
        statusEffect.onFade(unit);
        return statusEffect;
    }

    @Override
    public void turnOver() {
        for (StatusEffect statusEffect : statusEffects) {
            statusEffect.onTurnOver(unit);
            if (statusEffect.getCurrentDuration() == 0) {
                statusEffect.onFade(unit);
            }
        }
    }

    @Override
    public List<StatusEffect> getAllBuffs() {
        List<StatusEffect> tmp = new ArrayList<StatusEffect>();
        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect instanceof Buff) {
                tmp.add(statusEffect);
            }
        }
        return tmp;
    }

    @Override
    public List<StatusEffect> getAllDebuffs() {
        List<StatusEffect> tmp = new ArrayList<StatusEffect>();
        for (StatusEffect statusEffect : statusEffects) {
            if (statusEffect instanceof Debuff) {
                tmp.add(statusEffect);
            }
        }
        return tmp;
    }

    @Override
    public Iterator<StatusEffect> iterator() {
        return statusEffects.iterator();
    }

    @Override
    public StatusEffect get(int index) {
        return statusEffects.get(index);
    }

    @Override
    public int size() {
        return statusEffects.size();
    }

}
