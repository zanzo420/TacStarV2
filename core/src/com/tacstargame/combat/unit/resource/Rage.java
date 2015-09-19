package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.ModificatorStat;
import com.tacstargame.combat.unit.stats.Stats;

public class Rage extends ResourceImpl implements EventBusListener {

	public Rage(Unit unit, int currentResourceValue) {
		super(unit, currentResourceValue, 100, 100);
		EventBusImpl.getInstance().registerForEvent(this, EventBusEvent.UNIT_RESOURCE_CURRENTVALUE_CHANGED);
	}

	@Override
	public void updateResource(Stats stats) {
		setMaxValue(getBaseMaxValue() + stats.getStatValue(ModificatorStat.MAXRAGE));
	}

	@Override
	public void OnEventFired(EventBusEvent busEvent, Object... args) {
		Unit tmp = (Unit) args[0];
		if (unit.equals(tmp) && args[1] instanceof Health) {
			int difference = (Integer) args[2];
			if (difference < 0) {
				increaseCurrentValue(-difference);
			}
		}
		
	}
	
	

}
