package com.tacstargame.combat.unit.status;

public interface UnitStatusSet extends Iterable<UnitStatus>{
	
	/**
	 * Adds a UnitStatus to this UnitStatusSet.
	 * 
	 * @param unitStatus The UnitStatus that will be added.
	 */
	void addUnitStatus(UnitStatus unitStatus);
	
	/**
	 * Removes a UnitStatus from this UnitStatusSet.
	 * 
	 * @param unitStatus The UnitStatus that will be removed.
	 */
	void removeUnitStatus(UnitStatus unitStatus);
	
	/**
	 * Removes all UnitStati from this UnitStatusSet.
	 * 
	 */
	void removeAllUnitStati();
	
	boolean isUnitStatus(UnitStatus unitStatus);
}
