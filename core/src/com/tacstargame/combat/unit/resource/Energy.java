package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.ModificatorStat;
import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.status.UnitStatus;

public class Energy extends ResourceImpl {

	public Energy(Unit unit, int currentResourceValue) {
		super(unit, currentResourceValue, 150, 150);
	}

	@Override
	public void updateResource(Stats stats) {
		setMaxValue(getBaseMaxValue() + stats.getStatValue(ModificatorStat.MAXENERGY));
	}
	
	@Override
	public void turnOver() {
		super.turnOver();
		if (!unit.getUnitStatusSet().isUnitStatus(UnitStatus.DEAD)) {
			increaseCurrentValue((unit.getStats().getStatValue(BaseStat.SPEED) / 5) + 10);
		}	
	}

}
