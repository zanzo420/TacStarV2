/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.log;

/**
 * Prints the Combatlog on the default System ouput stream.
 *
 * @author Domenik
 */
public class StdCombatlogImpl implements CombatlogOutput {

    @Override
    public void printCombatLogEntry(CombatlogEntry combatLogEntry) {
        System.out.println(combatLogEntry.event + ": " + combatLogEntry.message);
    }
    
}
