/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.unit.Unit;
import java.util.List;

/**
 *
 * @author Domenik
 */
public interface AbilityQueue {
    	void queuePlayerAbility(Unit source, Unit target, Ability ability);
        void dequeuePlayerAbility(Unit unit);
        void queueEnemyAbility(Unit unit, Unit target, Ability ability);
        void dequeueEnemyAbility(Unit unit, Ability ability);
        void removeEntry(AbilityQueueEntry abilityQueueEntry);
        boolean hasAbilityQueued(Unit unit);
        AbilityQueueEntry getEntry(Unit unit);
        List<AbilityQueueEntry> getAllEntries();
}
