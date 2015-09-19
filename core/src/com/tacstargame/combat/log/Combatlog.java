/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.log;

/**
 * A Combatlog that stores all combat related events and possibly uses a CombatlogOutput to display/store
 * this information.
 *
 * @author Domenik
 */
public interface Combatlog {    
    void reset();
    void addCombatlogOutput(CombatlogOutput combatlogOutput);
    void removeCombatlogOutput(CombatlogOutput combatlogOutput);
    void setEnableOutput(boolean enableOutput);
}
