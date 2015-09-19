package com.tacstargame.combat.eventbus;

public enum EventBusEvent {
	UNIT_NAME_CHANGED,
	UNIT_RESOURCE_GAINED,
	UNIT_RESOURCE_MAXVALUE_CHANGED,
	UNIT_RESOURCE_CURRENTVALUE_CHANGED,
	UNIT_BUFF_APPLIED,
	UNIT_BUFF_FADED,
	UNIT_BUFF_REMOVED,
	UNIT_BUFF_DISPELLED,
	UNIT_DEBUFF_APPLIED,
	UNIT_DEBUFF_FADED,
	UNIT_DEBUFF_REMOVED,
	UNIT_DEBUFF_DISPELLED,
	UNIT_STATS_CHANGED,
	UNIT_STATUS_ADDED,
	UNIT_STATUS_REMOVED,
	UNIT_GEAR_CHANGED,
	UNIT_STATE_CHANGED,
	UNIT_MODEL_CHANGED,
	UNIT_ABILITY_CAST,
	UNIT_ABILITY_ADDED,
	UNIT_ABILITY_REMOVED,
	COMBAT_ABILITY_CAST_SUCCESS,
	COMBAT_ABILITY_CAST_FAILURE,
        COMBAT_ABILITY_CAST_FAILURE_INCAPACITATED,
        COMBAT_ABILITY_CAST_FAILURE_SOURCE_DEAD,
        COMBAT_ABILITY_CAST_FAILURE_RESOURCE_MISSING,
        COMBAT_ABILITY_CAST_FAILURE_MISS,
        COMBAT_ABILITY_CAST_FAILURE_DODGE,
        COMBAT_ABILITY_CAST_FAILURE_PARRY,
	COMBAT_STATE_CHANGED,
	COMBAT_BACKGROUND_CHANGED,
	COMBAT_MUSIC_CHANGED
}
