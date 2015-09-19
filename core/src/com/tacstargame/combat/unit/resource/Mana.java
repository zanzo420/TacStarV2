package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.Stats;

public class Mana extends ResourceImpl {

	public Mana(Unit unit, int currentResourceValue, int maxResourceValue,
			int baseMaxValue) {
		super(unit, currentResourceValue, maxResourceValue, baseMaxValue);
		
	}

	@Override
	public void updateResource(Stats stats) {
		setMaxValue(getBaseMaxValue() + stats.getStatValue(BaseStat.INTELLECT) * 3);
	}


}
