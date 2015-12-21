package com.tacstargame.combat.unit.character;

import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.ability.abilities.Mindblast;
import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.core.CombatImpl;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;
import com.tacstargame.combat.log.Combatlog;
import com.tacstargame.combat.log.CombatlogImpl;
import com.tacstargame.combat.log.StdCombatlogImpl;
import com.tacstargame.combat.statuseffect.impl.GiftOfTheBear;
import com.tacstargame.combat.unit.resource.ComboPoints;
import com.tacstargame.combat.unit.resource.Rage;
import com.tacstargame.combat.unit.stats.BaseStat;
import com.tacstargame.combat.unit.stats.ModificatorStat;


public class Lenor extends CharacterImpl {

	public Lenor() {
		super("Lenor");	
                setPrimaryResource(new Rage(this, 100));
		setSecondaryResource(new ComboPoints(this, 1));
		getBaseStats().increaseStat(BaseStat.STAMINA, 5);
		getBaseStats().increaseStat(ModificatorStat.MAXRAGE, 15);
		getBaseStats().increaseStat(BaseStat.SPEED, 15);
	}
	
	public static void main(String ... args) {
		Character character = new Lenor();
                Character enemy = new Lenor();
                Ability ability = new Mindblast();
                Combat combat = new CombatImpl();

		EventBusListener listener = new EventBusListener() {
			
			@Override
			public void OnEventFired(EventBusEvent busEvent, Object... args) {
				System.out.println("Event: " + busEvent + " Arg1: " + args[0] + " Arg2: " + args[1]);
			}
		};
		EventBusImpl.getInstance().registerForMultipleEvents(listener, EventBusEvent.values());
                Combatlog combatlog = new CombatlogImpl(new StdCombatlogImpl());
                //enemy.getStatusEffectSet().addStatusEffect(character, new GiftOfTheBear());
                combat.addPlayer(character);
                combat.addEnemy(enemy);
                combat.queuePlayerAbility(character, enemy, ability);
                combat.roundReady();
                System.out.println("HP: " + enemy.getHealth().getCurrentValue() + "/" + enemy.getHealth().getMaxValue());
                combat.calculateStep();
//		System.out.println("HP: " + character.getHealth().getMaxValue());
//		System.out.println("MANA: " + character.getSecondaryResource().getCurrentValue() + "/" +  character.getSecondaryResource().getMaxValue());
//		System.out.println("---------------------------------------------");
		
                
//		character.getGearSet().equip(new RingOfTheProtector());
//		character.getSecondaryResource().turnOver();
//		System.out.println("HP: " + character.getHealth().getMaxValue());
//		System.out.println("MANA: " + character.getSecondaryResource().getCurrentValue() + "/" +  character.getSecondaryResource().getMaxValue());
//		System.out.println("---------------------------------------------");
//		character.getGearSet().equip(new FeetOfTheWalker());
//		System.out.println("HP: " + character.getHealth().getMaxValue());
//		System.out.println("MANA: " + character.getSecondaryResource().getCurrentValue() + "/" +  character.getSecondaryResource().getMaxValue());
//		System.out.println("---------------------------------------------");
//		character.getGearSet().unequip(GearSlot.RING);
//		System.out.println("HP: " + character.getHealth().getMaxValue());
//		System.out.println("MANA: " + character.getSecondaryResource().getCurrentValue() + "/" +  character.getSecondaryResource().getMaxValue());
//		System.out.println("---------------------------------------------");
//		character.getStatusEffectSet().dispellStatusEffect(new GiftOfTheBear());
//		character.getGearSet().unequip(GearSlot.FEET);
		System.out.println("HP: " + enemy.getHealth().getCurrentValue() + "/" + enemy.getHealth().getMaxValue());
		System.out.println(character.getSecondaryResource().getClass().getSimpleName() + ": " + character.getSecondaryResource().getCurrentValue() + "/" +  character.getSecondaryResource().getMaxValue());

	}

}
