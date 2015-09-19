package com.tacstargame.combat.unit.stats;

public interface Stats {
	
	/**
	 * Returns the value of a stat in the StatSet.
	 * 
	 * @param stat The stat you want to get.
	 * @return The value of the stat.
	 */
	int getStatValue(Stat stat);
	
	/**
	 * Increases a stat value.
	 * 
	 * @param stat The stat that will be increased.
	 * @param value The value by which the stat will be increased.
	 */
	void increaseStat(Stat stat, int value);
	
	/**
	 * Adds a StatSet to this StatSet (permanent) and returns it.
	 * 
	 * @param stats
	 */
	Stats increaseStats(Stats stats);
	/**
	 * Sets a stat value.
	 * 
	 * @param stat The stat that will be set.
	 * @param value The new value of the stat.
	 */
	void setStat(Stat stat, int value);
	
	/**
	 * Adds a StatSet to this one (not permanent) and returns it.
	 * 
	 * @param stats StatSet that will be added.
	 * @return Result of the addition.
	 */
	Stats add(Stats stats);
}
