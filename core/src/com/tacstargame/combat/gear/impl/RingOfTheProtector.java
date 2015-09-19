package com.tacstargame.combat.gear.impl;

import com.tacstargame.combat.gear.GearSlot;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.ResistanceStat;

public class RingOfTheProtector extends GearImpl {

	public RingOfTheProtector() {
		super("Ring of the Protector", GearSlot.RING);
		stats.increaseStat(BaseStat.STAMINA, 5);
		stats.increaseStat(ResistanceStat.ARMOR, 10);
		stats.increaseStat(BaseStat.INTELLECT, 5);
	}

}
