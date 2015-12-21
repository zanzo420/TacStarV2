/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.combat.unit.resource;

import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.stats.ModificatorStat;
import com.tacstargame.combat.unit.stats.Stats;

/**
 *
 * @author Domenik Irrgang
 */
public class ComboPoints extends ResourceImpl {

    public ComboPoints(Unit unit, int currentResourceValue) {
        super(unit, currentResourceValue, 3, 3);
    }

    @Override
    public void updateResource(Stats stats) {
        setMaxValue(getBaseMaxValue() + stats.getStatValue(ModificatorStat.MAXCOMBOPOINTS));
    }
    
}
