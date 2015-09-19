package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.unit.Unit;
import java.util.List;

public interface CombatCalculator {
    Unit getFastestUnit(List<Unit> units);
    int calculateDamageAbilityValue(Ability ability, Unit source, Unit target);
    int calculateHealAbilityValue(Ability ability, Unit source, Unit target);
    int calculateAbilityResistAmount(int amount, Ability ability, Unit source, Unit target);
    int calculateBlockedAmount(int value, Ability ability, Unit source, Unit target);
    int calculateCriticalDamageAmount(int value, Ability ability, Unit source, Unit target);
    int calculateCriticalHealAmount(int value, Ability ability, Unit source, Unit target);
    boolean calculateAbilityHit(Ability ability, Unit source, Unit target);
    boolean calculateAbilityTotalResist(Ability ability, Unit source, Unit target);
    boolean calculateAbilityDodge(Ability ability, Unit source, Unit target);
    boolean calculateAbilityParry(Ability ability, Unit source, Unit target);
    boolean calculateAbilityBlock(Ability ability, Unit source, Unit target);
    boolean calculateAbilityCrit(Ability ability, Unit source, Unit target);
    boolean calculateAbilityReflect(Ability ability, Unit source, Unit target);
    int calculateAbilityCost(Ability ability, Unit source, Unit target);
    boolean isAbilityCastable(Ability ability, Unit source);
    double getBaseMissChance();
    double getBaseCritDamage();
    double getBaseCritHeal();
    double getBaseDodgeChance();
    double getBaseParryChance();
    double getBaseBlockChance();
    double getBaseResistChance();
}
