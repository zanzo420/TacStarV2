/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.combat.ability.abilities;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.ability.AbilityDamage;
import com.tacstargame.combat.ability.AbilityDamageType;
import com.tacstargame.combat.ability.AbilityTarget;
import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.resource.Mana;
import com.tacstargame.combat.unit.resource.Resource;

/**
 *
 * @author Domenik Irrgang
 */
public class Mindblast implements Ability {

    @Override
    public void execute(Combat combat, Unit source, Unit target) {
      
    }

    @Override
    public AbilityDamage getAbilityDamage() {
        return AbilityDamage.SHADOW;
    }

    @Override
    public Resource getAbilityResource() {
        return new Mana(null, 0,0,0);
    }

    @Override
    public AbilityTarget getAbilityTarget() {
        return AbilityTarget.SINGLE;
    }

    @Override
    public AbilityDamageType getAbilityDamageType() {
        return AbilityDamageType.DAMAGE;
    }

    @Override
    public int getResourceCost() {
        return 5;
    }

    @Override
    public boolean canCrit() {
        return true;
    }

    @Override
    public double getCritChance() {
        return 0;
    }

    @Override
    public double getMissChance() {
        return 0;
    }

    @Override
    public boolean isReflectable() {
        return true;
    }

    @Override
    public int getAbilityValue(Unit source) {
        return 10;
    }

    @Override
    public String getName() {
        return "Mindblast";
    }
    
}
