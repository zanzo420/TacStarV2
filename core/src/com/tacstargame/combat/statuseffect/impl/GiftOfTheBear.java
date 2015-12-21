package com.tacstargame.combat.statuseffect.impl;

import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.statuseffect.Buff;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.ResistanceStat;

public class GiftOfTheBear extends Buff {
	
	private int value = 2;

	public GiftOfTheBear() {
		super("Gift of the Bear", "Increases Stamina by 10 for 5 rounds.", 5, true);
		
	}
	
	@Override
	public void onApply(Combat combat, Unit source, Unit target) {
		super.onApply(combat, source, target);
		target.getStatusEffectStats().increaseStat(ResistanceStat.SHADOWRESISTANCE, value);
	}

	@Override
	public void onFade(Unit target) {
		super.onFade(target);
		target.getStatusEffectStats().increaseStat(ResistanceStat.SHADOWRESISTANCE, -value);
	}

	@Override
	public void onRemove(Unit target) {
		super.onRemove(target);
		target.getStatusEffectStats().increaseStat(ResistanceStat.SHADOWRESISTANCE, -value);
	}

	@Override
	public void onDispell(Unit target) {
		super.onDispell(target);
		target.getStatusEffectStats().increaseStat(ResistanceStat.SHADOWRESISTANCE, -value);
		target.getSecondaryResource().increaseCurrentValue(-10);
	}
	
}
