/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tacstargame.combat.core;

/**
 * Checks if combat has been won.
 * 
 * @author Domenik
 */
public interface CombatResult {
    /**
     * Checks for the result of a combat.
     * 
     * @param combat
     * @return Returns -1 if combat is lost.
     *         0 if it is still running.
     *         1 if it has been won.
     */
    byte checkCombatResult(Combat combat);
}
