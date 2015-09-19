package com.tacstargame.combat.gear;

import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.character.Character;

public interface Gear {
	
	/**
	 * Is invoked if Gear is added to a GearSet.
	 * 
	 * @param unit The Unit that holds the GearSet.
	 */
	void onEquip(Character character);
	
	/**
	 * Is invoked if Gear is removed from a GearSet.
	 * 
	 * @param unit
	 */
	void onUnEquip(Character character);
	
	/**
	 * Returns the Stats that his
	 * 
	 * @return The Stats of the Gear.
	 */
	Stats getStats();
	
	/**
	 * Returns the OnEquip description of that Gear. 
	 * 
	 * @return null if Gear has no OnEquip effect.
	 */
	String getOnEquipDescription();
	
	/**
	 * Returns the GearSlot of that Gear.
	 * 
	 * @return The GearSlot.
	 */
	GearSlot getGearSlot();
	
	/**
	 * Returns the name of the Gear.
	 * 
	 * @return Name of the Gear.
	 */
	String getName();
}
