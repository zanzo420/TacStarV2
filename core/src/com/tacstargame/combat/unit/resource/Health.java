package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.status.UnitStatus;

public class Health extends ResourceImpl {

	public Health(Unit unit, int currentResourceValue, int maxResourceValue,
			int baseMaxValue) {
		super(unit, currentResourceValue, maxResourceValue, baseMaxValue);		
	}

	@Override
	public void updateResource(Stats stats) {
		setMaxValue(getBaseMaxValue() + stats.getStatValue(BaseStat.STAMINA) * 5);
	}
	
	@Override
	public void increaseCurrentValue(int value) {
		if (!unit.getUnitStatusSet().isUnitStatus(UnitStatus.DEAD)) {
			super.increaseCurrentValue(value);
		}	
	}
	
	@Override
	public void setCurrentValue(int value) {
		if (!unit.getUnitStatusSet().isUnitStatus(UnitStatus.DEAD)) {
			super.setCurrentValue(value);
		}	
	}



}
