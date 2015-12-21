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
import com.tacstargame.combat.unit.resource.Rage;
import com.tacstargame.combat.unit.resource.Resource;
import com.tacstargame.rendering.TextureResource;

/**
 *
 * @author Domenik Irrgang
 */
public class Renew extends AbilityImpl {

    public Renew() {
        super(3);
    }

    @Override
    public void execute(Combat combat, Unit source, Unit target) {
    
    }

    @Override
    public AbilityDamage getAbilityDamage() {
        return AbilityDamage.LIGHT;
    }

    @Override
    public Resource getAbilityResource() {
        return new Rage(null, 0);
    }

    @Override
    public AbilityTarget getAbilityTarget() {
        return AbilityTarget.GROUP;
    }

    @Override
    public AbilityDamageType getAbilityDamageType() {
        return AbilityDamageType.HEAL;
    }

    @Override
    public int getResourceCost() {
        return 10;
    }

    @Override
    public boolean canCrit() {
        return true;
    }

    @Override
    public double getCritChance() {
        return 80;
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
        return 20;
    }

    @Override
    public String getName() {
        return "Renew";
    }

    @Override
    public Texture getIcon() {
        return TextureResource.get(TextureResource.ICON_ABILITY_RENEW);
    }
    
}
