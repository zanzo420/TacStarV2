package com.tacstargame.combat.unit;

import com.tacstargame.combat.ability.abilityset.AbilitySet;
import com.tacstargame.combat.statuseffect.StatusEffectSet;
import com.tacstargame.combat.unit.resource.Resource;
import com.tacstargame.combat.unit.stats.Stats;
import com.tacstargame.combat.unit.status.UnitStatusSet;

public interface Unit {
	Resource getHealth();
	void setHealth(Resource resource);
	Resource getPrimaryResource();
	void setPrimaryResource(Resource resource);
	Resource getSecondaryResource();
	void setSecondaryResource(Resource resource);
	StatusEffectSet getStatusEffectSet();
	UnitStatusSet getUnitStatusSet();
	AbilitySet getAbilitySet();
	Stats getBaseStats();
	Stats getStatusEffectStats();
	Stats getStats();
	String getName();
}
