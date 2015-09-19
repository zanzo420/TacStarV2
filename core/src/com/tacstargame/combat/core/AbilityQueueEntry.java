/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.unit.Unit;

/**
 * An entry for the ability queue in the combat system.
 * 
 * @author Domenik
 */
public class AbilityQueueEntry {
    public Unit source;
    public Ability ability;
    public Unit target;

    public AbilityQueueEntry(Unit source, Ability ability, Unit target) {
        this.source = source;
        this.ability = ability;
        this.target = target;
    }
}
