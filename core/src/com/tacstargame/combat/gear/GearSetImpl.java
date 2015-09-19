package com.tacstargame.combat.gear;

import java.util.HashMap;
import java.util.Iterator;

import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.character.Character;

public class GearSetImpl implements GearSet {
	
	private Character character;
	private HashMap<GearSlot, Gear> gearSet = new HashMap<GearSlot, Gear>();
	
	public GearSetImpl(Character character) {
		this.character = character;
		initGearSet();
	}
	
	private void initGearSet() {
		for (GearSlot gearSlot : GearSlot.values()) {
			gearSet.put(gearSlot, null);
		}
	}

	@Override
	public Gear equip(Gear gear) {
		Gear tmp = gearSet.get(gear.getGearSlot());
		if (tmp != null) { tmp.onUnEquip(character); }
		gearSet.put(gear.getGearSlot(), gear);
		gear.onEquip(character);
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_GEAR_CHANGED, character, gear.getGearSlot(), gear);
		return tmp;
	}

	@Override
	public Gear unequip(GearSlot gearSlot) {
		Gear tmp = gearSet.get(gearSlot);
		gearSet.put(gearSlot, null);
		tmp.onUnEquip(character);
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_GEAR_CHANGED, character, gearSlot, null);
		return tmp;
	}

	@Override
	public Gear getGear(GearSlot gearSlot) {
		return gearSet.get(gearSlot);
	}

	@Override
	public Iterator<Gear> iterator() {
		return gearSet.values().iterator();
	}

	@Override
	public Stats getGearStats() {
		Stats tmp = null;
		for (Gear gear : this) {
			if (gear != null) { tmp = gear.getStats().add(tmp); }
		}
		return tmp;
	}

}
