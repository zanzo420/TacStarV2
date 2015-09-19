package com.tacstargame.combat.ability;

import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.resource.Resource;

public abstract class AbilityDecorator implements Ability {
	
	private Ability ability;
	
	public AbilityDecorator(Ability ability) {
		this.ability = ability;
	}

	@Override
	public void execute(Combat combat, Unit source, Unit target) {
		ability.execute(combat, source, target);
	}

	@Override
	public AbilityDamage getAbilityDamage() {
		return ability.getAbilityDamage();
	}

	@Override
	public Resource getAbilityResource() {
		return ability.getAbilityResource();
	}

	@Override
	public AbilityTarget getAbilityTarget() {
		return ability.getAbilityTarget();
	}

	@Override
	public AbilityDamageType getAbilityDamageType() {
		return ability.getAbilityDamageType();
	}

	@Override
	public int getResourceCost() {
		return ability.getResourceCost();
	}

	@Override
	public double getCritChance() {
		return ability.getCritChance();
	}

	@Override
	public double getMissChance() {
		return ability.getMissChance();
	}
        
        @Override 
        public boolean isReflectable() {
            return ability.isReflectable();
        }

	@Override
	public int getAbilityValue(Unit source) {
            return ability.getAbilityValue(source);
	}

	@Override
	public String getName() {
		return ability.getName();
	}

	@Override
	public boolean canCrit() {
		return ability.canCrit();
	}

}
