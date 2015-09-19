/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.combat.log;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.eventbus.EventBus;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;
import com.tacstargame.combat.statuseffect.Buff;
import com.tacstargame.combat.unit.Unit;
import java.util.ArrayList;
import java.util.List;

/**
 * Standard implementation of the Combatlog interface.
 *
 * @author Domenik
 */
public class CombatlogImpl implements Combatlog {

    private List<CombatlogOutput> outputs = new ArrayList<CombatlogOutput>();
    private boolean enableOutput = true;
    private EventBusListener combatlogger = new CombatlogEventbusListener();

    public CombatlogImpl(CombatlogOutput combatlogOutput) {
        this();
        outputs.add(combatlogOutput);
    }

    public CombatlogImpl() {
        EventBus eventbus = EventBusImpl.getInstance();
        eventbus.registerForMultipleEvents(combatlogger, EventBusEvent.values());
    }

    @Override
    public void reset() {
        outputs.clear();
    }

    @Override
    public void addCombatlogOutput(CombatlogOutput combatlogOutput) {
        outputs.add(combatlogOutput);
    }

    @Override
    public void removeCombatlogOutput(CombatlogOutput combatlogOutput) {
        outputs.remove(combatlogOutput);
    }

    @Override
    public void setEnableOutput(boolean enableOutput) {
        this.enableOutput = enableOutput;
    }

    private void outputCombatlogEntry(CombatlogEntry combatlogEntry) {
        if (enableOutput) {
            for (CombatlogOutput output : outputs) {
                output.printCombatLogEntry(combatlogEntry);
            }
        }
    }

    private class CombatlogEventbusListener implements EventBusListener {

        @Override
        public void OnEventFired(EventBusEvent busEvent, Object... args) {
            String message = "";
            switch (busEvent) {
                case UNIT_BUFF_APPLIED: {
                    Buff buff = (Buff) args[0];
                    Unit source = (Unit) args[1];
                    Unit target = (Unit) args[2];
                    message = String.format("%s has applied %s on %s.", source.getName(), buff.getName(), target.getName());
                    break;
                }
                case COMBAT_ABILITY_CAST_SUCCESS: {
                    Unit source = (Unit) args[0];
                    Unit target = (Unit) args[1];
                    Ability ability = (Ability) args[2];
                    int value = (Integer) args[3];
                    boolean crit = (Boolean) args[4];
                    boolean reflected = (Boolean) args[5];
                    boolean blocked = (Boolean) args[6];
                    int amountBlocked = (Integer) args[7];
                    int amountResisted = (Integer) args[8];
                    message = String.format("%s casted %s on %s. Value: %d Crit: %b Reflected: %b Blocked: %b AmountBlocked: %d AmountResisted: %d", source.getName(), ability.getName(), target.getName(), value, crit, reflected, blocked, amountBlocked, amountResisted);
                    break;
                }
                default:
                    return;

            }
            outputCombatlogEntry(new CombatlogEntry(busEvent, message));
        }

    }

}
