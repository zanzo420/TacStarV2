package com.tacstargame.combat.unit;

import com.tacstargame.combat.ability.abilityset.AbilitySet;
import com.tacstargame.combat.ability.abilityset.AbilitySetImpl;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;
import com.tacstargame.combat.statuseffect.StatusEffectSet;
import com.tacstargame.combat.statuseffect.StatusEffectSetImpl;
import com.tacstargame.combat.unit.resource.Health;
import com.tacstargame.combat.unit.resource.Resource;
import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.stats.StatsImpl;
import com.tacstargame.combat.unit.status.UnitStatusSet;
import com.tacstargame.combat.unit.status.UnitStatusSetImpl;

public abstract class UnitImpl implements Unit, EventBusListener {
	
	private Resource health;
	private Resource primaryResource;
	private Resource secondaryResource; 
	
	private StatusEffectSet statusEffectSet;
	
	private UnitStatusSet unitStatusSet;
	
	private AbilitySet abilitySet;
	
	private Stats baseStats;
	private Stats statusEffectStats;
	
	private String name;
	
	public UnitImpl(String name) {
		this.name = name;
		this.health = new Health(this, 20, 20, 20);
		this.primaryResource = null;
		this.secondaryResource = null;
		statusEffectSet = new StatusEffectSetImpl(this);
		unitStatusSet = new UnitStatusSetImpl(this);
		abilitySet = new AbilitySetImpl(20);
		baseStats = new StatsImpl(this);
		statusEffectStats = new StatsImpl(this);
		EventBusImpl.getInstance().registerForMultipleEvents(this, EventBusEvent.UNIT_GEAR_CHANGED, EventBusEvent.UNIT_STATS_CHANGED);
	}
	
	@Override
	public Resource getHealth() {
		return health;
	}
	
	@Override
	public void setHealth(Resource resource) {
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_GAINED, this, health, resource);
		health = resource;
	}
	
	@Override
	public Resource getPrimaryResource() {
		return primaryResource;
	}
	
	@Override
	public Resource getSecondaryResource() {
		return secondaryResource;
	}
	
	@Override
	public StatusEffectSet getStatusEffectSet() {
		return statusEffectSet;
	}
	
	@Override
	public AbilitySet getAbilitySet() {
		return abilitySet;
	}
	
	@Override
	public Stats getBaseStats() {
		return baseStats;
	}
	
	@Override
	public Stats getStatusEffectStats() {
		return statusEffectStats;
	}
	
	@Override
	public Stats getStats() {
		return baseStats.add(statusEffectStats);
	}

	@Override
	public UnitStatusSet getUnitStatusSet() {
		return unitStatusSet;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void OnEventFired(EventBusEvent busEvent, Object... args) {
		switch (busEvent) {
		case UNIT_STATS_CHANGED:
		case UNIT_GEAR_CHANGED:
			if ((this.equals((Unit) args[0]))) {
				Stats tmp = getStats();
				health.updateResource(tmp);
				if (primaryResource != null) { primaryResource.updateResource(tmp); }
				if (secondaryResource != null) { secondaryResource.updateResource(tmp); }
			}
			break;
		}
	}

	@Override
	public void setPrimaryResource(Resource resource) {
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_GAINED, this, primaryResource, resource);
		this.primaryResource = resource;
	}

	@Override
	public void setSecondaryResource(Resource resource) {
		EventBusImpl.getInstance().fireEvent(EventBusEvent.UNIT_RESOURCE_GAINED, this, primaryResource, resource);
		this.secondaryResource = resource;
	}
	
	
}
