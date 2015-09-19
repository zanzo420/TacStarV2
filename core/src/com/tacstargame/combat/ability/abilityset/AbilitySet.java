package com.tacstargame.combat.ability.abilityset;

import com.tacstargame.combat.ability.Ability;

public interface AbilitySet extends Iterable<Ability> {
	
	/**
	 * Adds an Ability to the AbilitySet.
	 * 
	 * @param ability The Ability that will be added.
	 */
	void addAbility(Ability ability);
	
	/**
	 * Removes an Ablity from the AbilitySet.
	 * 
	 * @param ability The Ability that will be removed.
	 */
	void removeAbility(Ability ability);
	
	/**
	 * Returns the maximum number of Abilities that this AbilitySet can contain.
	 * 
	 * @return The maximum number of Abilities of this AbilitySet.
	 */
	int getAbilityCap();
	
	/**
	 * Sets the maximum number of Abilities that this AbilitySet can contain to AbilityCap.
	 * 
	 * @param abilityCap The new AbilityCap.
	 */
	void setAbilityCap(int abilityCap);
}
