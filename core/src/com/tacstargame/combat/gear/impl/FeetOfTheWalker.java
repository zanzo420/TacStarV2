package com.tacstargame.combat.gear.impl;

import com.tacstargame.combat.gear.GearSlot;
import com.tacstargame.combat.unit.stats.BaseStat;

public class FeetOfTheWalker extends GearImpl {

	public FeetOfTheWalker() {
		super("Feet of the Walker", GearSlot.FEET);
		stats.increaseStat(BaseStat.STAMINA, 20);
	}

}
