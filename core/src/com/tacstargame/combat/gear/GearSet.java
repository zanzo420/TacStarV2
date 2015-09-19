package com.tacstargame.combat.gear;

import com.tacstargame.combat.unit.stats.Stats;

public interface GearSet extends Iterable<Gear> {
	
	/**
	 * Equips a Gear to its GearSlot.
	 * 
	 * @param gear The Gear that will be equiped.
	 * @return The Gear that was on that slot before.
	 */
	Gear equip(Gear gear);
	
	/**
	 * Unequips Gear from a specific gearSlot.
	 * 
	 * @param gearSlot The GearSlot.
	 * @return The Gear that was on that GearSlot.
	 */
	Gear unequip(GearSlot gearSlot);
	
	/**
	 * Returns the Gear in a specific GearSlot.
	 * 
	 * @param gearSlot The GearSlot which will be returned.
	 * @return The Gear in GearSlot.
	 */
	Gear getGear(GearSlot gearSlot);
	
	/**
	 * Returns all Stats of the GearSet.
	 * 
	 * @return Sum of all Stats.
	 */
	Stats getGearStats();
}
