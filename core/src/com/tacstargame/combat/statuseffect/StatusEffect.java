package com.tacstargame.combat.statuseffect;

import com.tacstargame.combat.unit.Unit;

public interface StatusEffect {
	
	/**
	 * Is invoked if the StatusEffect is applied to a Unit.
	 * 
	 * @param source Unit that applied the StatusEffect.
	 * @param target Unit the StatusEffect has been applied on.
	 */
	void onApply(Unit source, Unit target);
	
	/**
	 * Is invoked if the StatusEffect is regularly faded.
	 * 
	 * @param target The Unit the StatusEffect faded on.
	 */
	void onFade(Unit target);
	
	/**
	 * Is invoked if the StatusEffect is removed before it fades.
	 * 
	 * @param target The Unit the StatusEffect was removed from.
	 */
	void onRemove(Unit target);
	
	/**
	 * Is invoked if the StatusEffect is dispelled.
	 * 
	 * @param target The Unit the StatusEffect was dispelled from.
	 */
	void onDispell(Unit target);
	
	/**
	 * Is invoked if the StatusEffect lasted on turn.
	 * 
	 * @param target The Unit the StatusEffect lasted on.
	 */
	void onTurnOver(Unit target);
	
	/**
	 * Returns TRUE if StatusEffect is dispellable.
	 * 
	 * @return True if dispellable.
	 */
	boolean isDispellable();
	
	/**
	 * Returns the current duration of the StatusEffect.
	 * 
	 * @return Current Duration of StatusEffect.
	 */
	int getCurrentDuration();
	
	/**
	 * Return the full duration of the StatusEffect.
	 * 
	 * @return Full Duration of StatusEffect.
	 */
	int getFullFuration();
	
	/**
	 * Returns the name of the StatusEffect.
	 * 
	 * @return Name of the StatusEffect.
	 */
	String getName();
	
	/**
	 * Return the Description of the StatusEffect.
	 * 
	 * @return Description of the StatusEffect.
	 */
	String getDescription();
}
