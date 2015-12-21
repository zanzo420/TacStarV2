/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.combat.unit.character;

import com.tacstargame.combat.unit.resource.ComboPoints;
import com.tacstargame.combat.unit.resource.Energy;
import com.tacstargame.combat.unit.resource.Rage;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.ModificatorStat;

/**
 *
 * @author Domenik Irrgang
 */
public class EsElTee extends CharacterImpl {
    	public EsElTee() {
		super("EsElTee");	
                setPrimaryResource(new Energy(this, 100));
		setSecondaryResource(new ComboPoints(this, 1));
		getBaseStats().increaseStat(BaseStat.STAMINA, 5);
		getBaseStats().increaseStat(ModificatorStat.MAXRAGE, 15);
		getBaseStats().increaseStat(BaseStat.SPEED, 15);
	}
}
