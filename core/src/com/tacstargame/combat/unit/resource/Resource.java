package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.unit.stats.Stats;

public interface Resource {
	
	/**
	 * Sets the maximal Resource value to value.
	 * 
	 * @param value The new Resource cap.
	 */
	void setMaxValue(int value);
	
	/**
	 * Sets the current Resource value to value.
	 * 
	 * @param value The new current Resource value.
	 */
	void setCurrentValue(int value);
	
	/**
	 * Increases the current Resource value by value but not over the Resource cap.
	 * 
	 * @param value The value by which the current Resource value will be increased.
	 */
	void increaseCurrentValue(int value);
	
	/**
	 * Sets the BaseMaxValue to value.
	 * 
	 * @param value The new BaseMaxValue.
	 */
	void setBaseMaxValue(int value);
	
	/**
	 * Returns the BaseMaxValue.
	 * 
	 * @return The BaseMaxValue.
	 */
	int getBaseMaxValue();
	
	/**
	 * Increases the Resource cap.
	 * 
	 * @param value The value by which the Resource cap will be increased.
	 */
	void increaseMaxValue(int value);
	
	/**
	 * Returns the Resource cap.
	 * 
	 * @return The Resource cap.
	 */
	int getMaxValue();
	
	/**
	 * Return the current Resource value.
	 * 
	 * @return The current Resource value.
	 */
	int getCurrentValue();
	
	/**
	 * If Resource scales with Stats it can update itself here.
	 * 
	 * @param stats The Stats.
	 */
	void updateResource(Stats stats);
	
	/**
	 * Is invoked if one turn is over.
	 * 
	 */
	void turnOver();
}
