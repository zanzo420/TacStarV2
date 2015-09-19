package com.tacstargame.combat.unit.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.unit.Unit;

public class UnitStatusSetImpl implements UnitStatusSet {
	
	private List<UnitStatus> unitStati = new ArrayList<UnitStatus>();
	private Unit unit;
	
	public UnitStatusSetImpl(Unit unit) {
		this.unit = unit;
	}

	@Override
	public Iterator<UnitStatus> iterator() {
		return unitStati.iterator();
	}

	@Override
	public void addUnitStatus(UnitStatus unitStatus) {
		unitStati.add(unitStatus);
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_STATUS_ADDED, unit, unitStatus);
	}

	@Override
	public void removeUnitStatus(UnitStatus unitStatus) {
		unitStati.remove(unitStatus);
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_STATUS_REMOVED, unit, unitStatus);
	}

	@Override
	public void removeAllUnitStati() {
		for (UnitStatus unitStatus : this) {
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_STATUS_REMOVED, unit, unitStatus);
		}
		unitStati.clear();
	}

	@Override
	public boolean isUnitStatus(UnitStatus unitStatus) {
		return unitStati.contains(unitStatus);
	}

}
