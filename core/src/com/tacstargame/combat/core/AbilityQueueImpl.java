/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.unit.Unit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Domenik
 */
public class AbilityQueueImpl implements AbilityQueue {
    
    private List<AbilityQueueEntry> abilityQueue = new ArrayList<AbilityQueueEntry>();

    @Override
    public void queuePlayerAbility(Unit source, Unit target, Ability ability) {
        if (!hasAbilityQueued(source)) {
            abilityQueue.add(new AbilityQueueEntry(source, ability, target));
        }
    }

    @Override
    public void dequeuePlayerAbility(Unit unit) {
        AbilityQueueEntry entry;
        if (unit == null) return;
        for (int i = 0; i < abilityQueue.size(); i++) {
            entry = abilityQueue.get(i);
            if (entry.source.getName().equals(unit.getName())) {
                abilityQueue.remove(i);
                break;
            }
        }
    }

    @Override
    public void queueEnemyAbility(Unit unit, Unit target, Ability ability) {
        abilityQueue.add(new AbilityQueueEntry(unit, ability, target));
    }

    @Override
    public void dequeueEnemyAbility(Unit unit, Ability ability) {
        AbilityQueueEntry entry;
        for (int i = 0; i < abilityQueue.size(); i++) {
            entry = abilityQueue.get(i);
            if (entry.source.getName().equals(unit.getName()) && entry.ability.getName().equals(ability.getName())) {
                abilityQueue.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean hasAbilityQueued(Unit unit) {
        AbilityQueueEntry entry;
        for (int i = 0; i < abilityQueue.size(); i++) {
            entry = abilityQueue.get(i);
            if (entry.source.getName().equals(unit.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AbilityQueueEntry> getAllEntries() {
        return abilityQueue;
    }

    @Override
    public AbilityQueueEntry getEntry(Unit unit) {
        AbilityQueueEntry entry;
        for (int i = 0; i < abilityQueue.size(); i++) {
            entry = abilityQueue.get(i);
            if (entry.source.getName().equals(unit.getName())) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public void removeEntry(AbilityQueueEntry abilityQueueEntry) {
        abilityQueue.remove(abilityQueueEntry);
    }
    
}
