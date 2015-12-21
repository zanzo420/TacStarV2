/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.combat.ability.abilities;

import com.badlogic.gdx.graphics.Texture;
import com.tacstargame.combat.ability.AbilityDamage;
import com.tacstargame.combat.ability.AbilityDamageType;
import com.tacstargame.combat.ability.AbilityImpl;
import com.tacstargame.combat.ability.AbilityTarget;
import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.resource.Resource;
import com.tacstargame.rendering.TextureResource;

/**
 *
 * @author Domenik Irrgang
 */
public class CancelQueue extends AbilityImpl {

    public CancelQueue() {
        super(1);
    }

    @Override
    public void execute(Combat combat, Unit source, Unit target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbilityDamage getAbilityDamage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resource getAbilityResource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbilityTarget getAbilityTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbilityDamageType getAbilityDamageType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getResourceCost() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canCrit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getCritChance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getMissChance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isReflectable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAbilityValue(Unit source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Texture getIcon() {
        return TextureResource.get(TextureResource.COMBAT_ABILITYQUEUE_NOTQUEUED);
    }
    
}
