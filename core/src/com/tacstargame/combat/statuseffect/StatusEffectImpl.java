package com.tacstargame.combat.statuseffect;

import com.tacstargame.combat.unit.Unit;

public abstract class StatusEffectImpl implements StatusEffect {

	protected boolean dispellable;
	protected int fullDuration;
	protected int currentDuration;
	protected String name;
	protected String description;
	protected Unit source;
	
	/**
	 * Creates an instance of a StatusEffectImpl object.
	 * 
	 * @param name Name of the StatusEffect.
	 * @param description Description of the StatusEffect.
	 * @param fullDuration Maximal Duration of the StatusEffect.
	 * @param dispellable If TRUE StatusEffect can be removed by Abilities.
	 */
	public StatusEffectImpl(String name, String description, int fullDuration, boolean dispellable) {
		this.name = name;
		this.description = description;
		this.fullDuration = fullDuration;
		this.dispellable = dispellable;
	}
	
	@Override
	public void onTurnOver(Unit target) {
		currentDuration--;
	}

	@Override
	public boolean isDispellable() {
		return dispellable;
	}

	@Override
	public int getCurrentDuration() {
		return currentDuration;
	}

	@Override
	public int getFullFuration() {
		return fullDuration;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void onApply(Unit source, Unit target) {
		this.source = source;
	}
	
}
