package com.tacstargame.combat.core;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.log.Combatlog;
import com.tacstargame.combat.unit.Unit;
import java.util.List;
import java.util.Map;

public interface Combat {
	void calculateStep();
	void roundReady();
        void roundOver();
        void addPlayer(Unit unit);
        void addEnemy(Unit unit);
        void removePlayer(Unit unit);
        void removeEnemy(Unit unit);
        void updateBuffs();
        AbilityQueue getAbilityQueue();
        List<Unit> getPlayerGroup();
        List<Unit> getEnemyGroup();
        void queuePlayerAbility(Unit source, Unit target, Ability ability);
        void dequeuePlayerAbility(Unit unit);
        boolean isCalculating();
        boolean hasAbilityQueued(Unit unit);
        void setCombatCalculator(CombatCalculator combatCalculator);
        Combatlog getCombatlog();
        int getCurrentRound();
}
