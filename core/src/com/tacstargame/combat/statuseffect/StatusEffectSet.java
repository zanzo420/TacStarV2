package com.tacstargame.combat.statuseffect;

import com.tacstargame.combat.core.Combat;
import java.util.List;

import com.tacstargame.combat.unit.Unit;

public interface StatusEffectSet extends Iterable<StatusEffect> {

    /**
     * Adds a StatusEffect to this Set.
     *
     * @param combat Combat the Buff is casted in.
     * @param source Source of the StatusEffect.
     * @param statusEffect The StatusEffect that will be added.
     */
    void addStatusEffect(Combat combat, Unit source, StatusEffect statusEffect);

    /**
     * Removes the StatusEffect from the Set.
     *
     * @param statusEffect The StatusEffect that will be removed.
     * @return The StatusEffect that was removed.
     */
    StatusEffect removeStatusEffect(StatusEffect statusEffect);

    /**
     * Dispells a StatusEffect.
     *
     * @param statusEffect StatusEffect that will be dispelled.
     * @return The dispelled StatusEffect.
     */
    StatusEffect dispellStatusEffect(StatusEffect statusEffect);

    /**
     * StatusEffect is faded from Unit an needs to be removed from the Set.
     *
     * @param statusEffect The StatusEffect that faded.
     * @return The faded StatusEffect.
     */
    StatusEffect fadeStatusEfect(StatusEffect statusEffect);

    /**
     * Is invoked if a Turn of the Combat is over.
     *
     */
    void turnOver();

    /**
     * Returns the StatusEffect at index.
     *
     * @param index Index of the StatusEffect you want to get.
     * @return The StatusEffect.
     */
    StatusEffect get(int index);

    /**
     * Returns all Buffs of this Set.
     *
     * @return Buffs of this Set.
     */
    List<StatusEffect> getAllBuffs();

    /**
     * Returns all Debuffs of this Set.
     *
     * @return Debuffs of this Set.
     */
    List<StatusEffect> getAllDebuffs();

    /**
     * Returns the number of StatusEffects in the set.
     *
     * @return Number of StatusEffects in the set.
     */
    int size();
}
