package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.unit.Unit;

public abstract class ResourceImpl implements Resource {
	
	private int maxResourceValue;
	private int currentResourceValue;
	private int baseMaxValue;
	protected Unit unit;
	
	public ResourceImpl(Unit unit, int currentResourceValue, int maxResourceValue, int baseMaxValue) {
		this.maxResourceValue = maxResourceValue;
		this.currentResourceValue = currentResourceValue;
		this.currentResourceValue = getValue(this.currentResourceValue);
		this.baseMaxValue = baseMaxValue;
		this.unit = unit;
	}

	@Override
	public void setMaxValue(int value) {
		int difference = value - maxResourceValue;
		if (difference != 0) {
			maxResourceValue = (value > 0) ? value : 0;
			currentResourceValue = getValue(currentResourceValue);
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_MAXVALUE_CHANGED, unit, this, difference);
		}	
	}
	
	@Override
	public void setCurrentValue(int value) {
		int difference = value - currentResourceValue;
		if (difference != 0) {
			currentResourceValue = getValue(value);
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_CURRENTVALUE_CHANGED, unit, this, difference);
		}
	}

	@Override
	public void increaseCurrentValue(int value) {
		currentResourceValue += value;
		currentResourceValue = getValue(currentResourceValue);
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_CURRENTVALUE_CHANGED, unit, this, value);
	}

	@Override
	public void increaseMaxValue(int value) {
		if (value != 0) {
			maxResourceValue += value;
			maxResourceValue = (maxResourceValue < 0) ? 0 : maxResourceValue;
			currentResourceValue = getValue(currentResourceValue);
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_MAXVALUE_CHANGED, unit, this, value);
		}	
	}
	
	private int getValue(int value) {
		if (value < 0) {
			return 0;
		}
		if (value > maxResourceValue) {
			return maxResourceValue;
		}
		return value;
	}

	@Override
	public int getMaxValue() {
		return maxResourceValue;
	}

	@Override
	public int getCurrentValue() {
		return currentResourceValue;
	}
	
	@Override
	public int getBaseMaxValue() {
		return baseMaxValue;
	}
	
	@Override
	public void setBaseMaxValue(int value) {
		this.baseMaxValue = value;
	}

	@Override
	public void turnOver() {
	}
}
