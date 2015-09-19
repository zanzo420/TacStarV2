package com.tacstargame.combat.unit.character;

import com.tacstargame.combat.gear.GearSet;
import com.tacstargame.combat.gear.GearSetImpl;
import com.tacstargame.combat.unit.UnitImpl;
import com.tacstargame.combat.unit.stats.Stats;

public abstract class CharacterImpl extends UnitImpl implements Character {
	
	private GearSet gearSet;
	
	public CharacterImpl(String name) {
		super(name);
		gearSet = new GearSetImpl(this);
	}

	@Override
	public GearSet getGearSet() {
		return gearSet;
	}
	
	@Override
	public Stats getStats() {
		return super.getStats().add(gearSet.getGearStats());
	}


}
