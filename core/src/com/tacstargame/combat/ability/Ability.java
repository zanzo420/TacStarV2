package com.tacstargame.combat.ability;

import com.badlogic.gdx.graphics.Texture;
import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.resource.Resource;

public interface Ability {
	
	/**
	 * Is invoked if Ability was successfully casted.
	 * 
	 * @param combat Combat in which the Ability was casted.
	 * @param source Unit that used this Ability.
	 * @param target Unit that will be the target of this Ability.
	 */
	void execute(Combat combat, Unit source, Unit target);
	
	/**
	 * Returns the damage of this Ability base on its source.
	 * 
	 * @param source Source of the Ability.
	 * @return Damage of this Ability.
	 */
	AbilityDamage getAbilityDamage();
        
        /**
         * Set the remaining cooldown time to a new value.
         * 
         * @param remainingCooldown The new value.
         */
        void setRemainingCooldown(int remainingCooldown);
        
        /**
         * Returns the Maximum Cooldown Time.
         * 
         * @return Maximum Cooldown Time.
         */
        int getMaxCooldown();
        
        /**
         * Sets the Maximum Cooldown Time.
         * 
         * @param maxCooldown New Maximum Cooldown Time.
         */
        void setMaxCooldown(int maxCooldown);
        
        /**
         * Returns the Remaining Cooldown Time.
         * 
         * @return Remaining Cooldown Time.
         */
        int getRemainingCooldown();
	
	/**
	 * Returns which Resource this Ability consumes.
	 * 
	 * @return The Resource that this Ability Consumes.
	 */
	Resource getAbilityResource();
	
	/**
	 * Returns which Units this Ability will target.
	 * 
	 * @return The Ability's targets.
	 */
	AbilityTarget getAbilityTarget();
	
	/**
	 * Returns the AbilityDamageType.
	 * 
	 * @return The AbilityDamageType.
	 */
	AbilityDamageType getAbilityDamageType();
	
	/**
	 * Returns the costs of the Resource.
	 * 
	 * @return The Resource costs.
	 */
	int getResourceCost();
	
	/**
	 * Returns true if Ability is able to land a critical hit.
	 * 
	 * @return True if Ability can land a critical hit.
	 */
	boolean canCrit();
	
	/**
	 * Returns the extra CritChance of this Ability.
	 * 
	 * @return The extra CritChance.
	 */
	double getCritChance();
	
	/**
	 * Returns the extra MissChance of this Ability.
	 * 
	 * @return The extra MissChance.
	 */
	double getMissChance();
        
        /**
         * Returns true if ability can be reflected by the enemy.
         * 
         * @return True if ability can be reflected. 
         */
        boolean isReflectable();
	
	/**
	 * Returns the value of this Ability based on its source.
	 * 
	 * @param source The source of this Ability.
	 * @return
	 */
	int getAbilityValue(Unit source);
	
	/**
	 * Returns the name of this Ability.
	 * 
	 * @return The name of this Ability.
	 */
	String getName();
        
        /**
         * Returns the Icon of this Ability.
         * 
         * @return Icon of this Ability.
         */
        Texture getIcon();
}
