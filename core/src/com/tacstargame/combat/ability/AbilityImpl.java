/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.combat.ability;

import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;

/**
 *
 * @author Domenik Irrgang
 */
public abstract class AbilityImpl implements Ability, EventBusListener {
    
    private int remainingCooldown = 0;
    private int maxCooldown;
    
    public AbilityImpl(int maxCooldown) {  
        this.maxCooldown = maxCooldown;
        EventBusImpl.getInstance().registerForEvent(this, EventBusEvent.COMBAT_STATE_CHANGED);
    }

    @Override
    public void OnEventFired(EventBusEvent busEvent, Object... args) {
        if (busEvent.equals(EventBusEvent.COMBAT_STATE_CHANGED) && remainingCooldown > 0 && !((Boolean) args[0])) {
            remainingCooldown -= 1;
        }
    }

    @Override
    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    @Override
    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    @Override
    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }
    
    @Override
    public int getMaxCooldown() {
        return maxCooldown;
    }   
    
}
