package com.tacstargame.combat.gear.impl;

import com.tacstargame.combat.gear.Gear;
import com.tacstargame.combat.gear.GearSlot;
import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.stats.StatsImpl;
import com.tacstargame.combat.unit.character.Character;

public  class GearImpl implements Gear {
	
	GearSlot gearSlot;
	private String name;
	protected Stats stats;
	
	public GearImpl(String name, GearSlot gearSlot) {
		this.name = name;
		this.gearSlot = gearSlot;
		stats = new StatsImpl(null);
	}
	
	@Override
	public void onEquip(Character character) {
	}
	
	@Override
	public void onUnEquip(Character character) {
	}
	
	@Override
	public Stats getStats() {
		return stats;
	}
	
	@Override
	public String getOnEquipDescription() {
		return null;
	}
	
	@Override
	public GearSlot getGearSlot() {
		return gearSlot;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
