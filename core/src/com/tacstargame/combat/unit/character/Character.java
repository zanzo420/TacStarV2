package com.tacstargame.combat.unit.character;

import com.tacstargame.combat.gear.GearSet;
import com.tacstargame.combat.unit.Unit;

public interface Character extends Unit {
	GearSet getGearSet();
}
