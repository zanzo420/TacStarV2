/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.log;

import com.tacstargame.combat.eventbus.EventBusEvent;

/**
 * A Combatlog entry that contains an EventBusEvent with its appropiate Message.
 * 
 * @author Domenik
 */
public class CombatlogEntry {
    public EventBusEvent event;
    public String message;
    
    public CombatlogEntry(EventBusEvent event, String message) {
        this.event = event;
        this.message = message;
    }
}
