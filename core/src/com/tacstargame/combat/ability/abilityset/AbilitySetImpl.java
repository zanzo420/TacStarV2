package com.tacstargame.combat.ability.abilityset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;

public class AbilitySetImpl implements AbilitySet {
	
	private List<Ability> abilities = new ArrayList<Ability>();
	private int abilityCap;
	
	/**
	 * Creates an instance of a AbilitySet.
	 * 
	 * @param abilityCap The AbilityCap of this Set.
	 */
	public AbilitySetImpl(int abilityCap) {
		this.abilityCap = abilityCap;
	}

	@Override
	public void addAbility(Ability ability) {
		if (abilities.size() == abilityCap || abilities.contains(ability)) {
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_ABILITY_ADDED, false, ability);
		} else {
			abilities.add(ability);
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_ABILITY_ADDED, true, ability);
		}
	}

	@Override
	public void removeAbility(Ability ability) {
		if (abilities.contains(ability)) {
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_ABILITY_REMOVED, false, ability);
		} else {
			abilities.add(ability);
			EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_ABILITY_REMOVED, true, ability);
		}
	}

	@Override
	public int getAbilityCap() {
		return 0;
	}

	@Override
	public void setAbilityCap(int abilityCap) {
	}

	@Override
	public Iterator<Ability> iterator() {
		return abilities.iterator();
	}


	
}
