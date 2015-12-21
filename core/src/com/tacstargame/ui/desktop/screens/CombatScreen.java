package com.tacstargame.ui.desktop.screens;

import com.badlogic.gdx.math.Vector2;
import com.tacstargame.TacStar;
import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.ability.abilities.CancelQueue;
import com.tacstargame.combat.ability.abilities.Mindblast;
import com.tacstargame.combat.ability.abilities.Renew;
import com.tacstargame.combat.core.Combat;
import com.tacstargame.combat.core.CombatImpl;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.character.EsElTee;
import com.tacstargame.combat.unit.character.Kimuh;
import com.tacstargame.combat.unit.character.Lenor;
import com.tacstargame.combat.unit.character.Suu;
import com.tacstargame.input.KeyBindings;
import com.tacstargame.input.KeyListener;
import com.tacstargame.input.MouseAdapter;
import com.tacstargame.input.MouseListener;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.combat.AbilityIcon;
import com.tacstargame.ui.combat.AbilityQueueBar;
import com.tacstargame.ui.combat.Tooltip;
import com.tacstargame.ui.combat.Unitframe;
import com.tacstargame.ui.element.Background;
import com.tacstargame.ui.element.UiButton;
import com.tacstargame.ui.element.UiImage;
import com.tacstargame.ui.screens.AbstractScreen;
import com.tacstargame.ui.util.Measure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Domenik Irrgang
 */
public class CombatScreen extends AbstractScreen {

    private List<Unitframe> unitFrames = new ArrayList<Unitframe>();
    private AbilityQueueBar abilityQueueBar;
    private List<AbilityIcon> abilityIcons = new ArrayList<AbilityIcon>();
    private AbilityIcon cancelQueue;

    private UiButton lockButton;

    private CombatPhase combatPhase = CombatPhase.ABILITYSELECTION;
    private Combat combat;
    private Unit selectedPlayer;
    private AbilityIcon selectedAbilityIcon;
    private Unit selectedTarget;
    private boolean playerGroupTargeted = true;

    public CombatScreen(TacStar tacStar) {
        super(tacStar);
        background = new Background(TextureResource.get(TextureResource.TACSTAR_MAINMENUE_BACKGROUND));
        combat = new CombatImpl();
        
        initDebug();
        initUnitFrames();
        initAbilityQueueBar();
        initAbilityIcons();
        initLockButton();
        initTooltip();
        lockButton.addKeyListener(new KeyInput());
        selectCharacter(combat.getPlayerGroup().get(0));
    }

    private void initDebug() {
        Unit unit = new Lenor();
        Ability ability = new Mindblast();
        ability.setRemainingCooldown(2);
        unit.getAbilitySet().addAbility(ability);
        unit.getAbilitySet().addAbility(new Renew());
        unit.getAbilitySet().addAbility(new Mindblast());
        combat.addPlayer(unit);
        unit = new Suu();
        unit.getAbilitySet().addAbility(new Renew());
        combat.addPlayer(unit);
        unit = new Kimuh();
        unit.getAbilitySet().addAbility(new Mindblast());
        combat.addPlayer(unit);
        unit = new EsElTee();
        unit.getAbilitySet().addAbility(new Mindblast());
        combat.addPlayer(unit);
        combat.addEnemy(new Lenor());
        combat.addEnemy(new Lenor());
        combat.addEnemy(new Lenor());
        combat.addEnemy(new Lenor());
        combat.queuePlayerAbility(unit, unit, ability);
    }

    private void initAbilityQueueBar() {
        abilityQueueBar = new AbilityQueueBar(combat, new Vector2(30, 30), new Measure(combat.getPlayerGroup().size() * 50, 50));
        ui.addUiElement(abilityQueueBar);
    }
    
    private void initTooltip() {
        Tooltip tooltip = new Tooltip(new Vector2(30, 90), new Measure(200,200));
        ui.addUiElement(tooltip);
    }

    private void initLockButton() {
        lockButton = new UiButton(new Vector2(1720, 30), new Measure(170, 60));
        lockButton.addMouseListener(new LockButtonListener());
        ui.addUiElement(lockButton);
    }

    private void initUnitFrames() {
        int i = 0;
        for (Unit unit : combat.getPlayerGroup()) {
            Unitframe unitframe = new Unitframe(unit, new Vector2(30, 970 - i * 160), new Measure(100, 100));
            unitframe.addMouseListener(new UnitFrameListener(unit));
            unitframe.addMouseListener(new TargetSelectionListener(unit));
            ui.addUiElement(unitframe);
            i++;
        }

        i = 0;
        for (Unit unit : combat.getEnemyGroup()) {
            Unitframe unitframe = new Unitframe(unit, new Vector2(1770, 970 - i * 160), new Measure(100, 100));
            unitframe.addMouseListener(new TargetSelectionListener(unit));
            ui.addUiElement(unitframe);
            i++;
        }

    }

    private void selectCharacter(Unit unit) {
        selectedPlayer = unit;
        EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_CHARACTER_CHANGED, selectedPlayer);
        Iterator<Ability> abilities = unit.getAbilitySet().iterator();
        for (int i = 0; i < 4; i++) {
            Ability ability = (abilities.hasNext()) ? abilities.next() : null;
            abilityIcons.get(i).setAbility(ability);
        }
        cancelQueue.setVisible(combat.hasAbilityQueued(unit));
        selectAbilityIcon(abilityIcons.get(0));
    }

    private void selectAbilityIcon(AbilityIcon icon) {
        if (icon.getAbility() != null) {
            selectedAbilityIcon = icon;
            EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_ABILITY_CHANGED, icon);
        }
    }

    private void selectNextAbilityIcon() {
        for (int i = 0; i < abilityIcons.size() - 1; i++) {
            if (abilityIcons.get(i).equals(selectedAbilityIcon)) {
                selectAbilityIcon(abilityIcons.get(i + 1));
                break;
            }
        }
    }

    private void selectPreviousAbilityIcon() {
        for (int i = abilityIcons.size() - 1; i > 0; i--) {
            if (abilityIcons.get(i).equals(selectedAbilityIcon)) {
                selectAbilityIcon(abilityIcons.get(i - 1));
                break;
            }
        }
    }

    private void lockRound() {
        cancelQueue.setVisible(false);
        combat.roundReady();
        while (combat.isCalculating()) {
            combat.calculateStep();
        }
    }

    private void initAbilityIcons() {
        for (int i = 0; i < 4; i++) {
            AbilityIcon icon = new AbilityIcon(null, new Vector2((1920 / 2 - 2 * 110) + i * 110, 30), new Measure(100, 100));
            abilityIcons.add(icon);
            icon.addMouseListener(new AbilityIconListener(icon));
            ui.addUiElement(icon);
        }
        cancelQueue = new AbilityIcon(new CancelQueue(), new Vector2((1920 / 2 - 2 * 110) + 4 * 110, 30), new Measure(100, 100));
        cancelQueue.addMouseListener(new CancelQueueListener());
        ui.addUiElement(cancelQueue);
    }

    private void abilityIconClick(AbilityIcon icon) {
        if (!combat.hasAbilityQueued(selectedPlayer) && icon.getAbility() != null && icon.getAbility().getRemainingCooldown() == 0) {
            changeCombatPhase(CombatPhase.TARGETSELECTION);
            for (AbilityIcon abilityIcon : abilityIcons) {
                abilityIcon.setSelected(false);
            }
            selectAbilityIcon(icon);
            icon.setSelected(true);
            selectedTarget = combat.getEnemyGroup().get(0);
            playerGroupTargeted = false;
            EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_CHARACTER_CHANGED, (Object) null);
            EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_TARGET_CHANGED, selectedTarget);
        }
    }

    private void selectTarget(Unit target) {
        selectedTarget = target;
        EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_TARGET_CHANGED, selectedTarget);
    }

    private void selectNextTarget() {
        if (playerGroupTargeted) {
            for (int i = 0; i < combat.getPlayerGroup().size() - 1; i++) {
                if (combat.getPlayerGroup().get(i).equals(selectedTarget)) {
                    selectTarget(combat.getPlayerGroup().get(i + 1));
                    break;
                }
            }
        } else {
            for (int i = 0; i < combat.getEnemyGroup().size() - 1; i++) {
                if (combat.getEnemyGroup().get(i).equals(selectedTarget)) {
                    selectTarget(combat.getEnemyGroup().get(i + 1));
                    break;
                }
            }
        }
    }

    private void selectPreviousTarget() {
        if (playerGroupTargeted) {
            for (int i = combat.getPlayerGroup().size() - 1; i > 0; i--) {
                if (combat.getPlayerGroup().get(i).equals(selectedTarget)) {
                    selectTarget(combat.getPlayerGroup().get(i - 1));
                    break;
                }
            }
        } else {
            for (int i = combat.getEnemyGroup().size() - 1; i > 0; i--) {
                if (combat.getEnemyGroup().get(i).equals(selectedTarget)) {
                    selectTarget(combat.getEnemyGroup().get(i - 1));
                    break;
                }
            }
        }
    }

    private void changeTargetGroup() {
        playerGroupTargeted = !playerGroupTargeted;
        if (playerGroupTargeted) {
            selectedTarget = combat.getPlayerGroup().get(0);
        } else {
            selectedTarget = combat.getEnemyGroup().get(0);
        }
        EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_TARGET_CHANGED, selectedTarget);
    }

    private void undoAbilityIconClick() {
        changeCombatPhase(CombatPhase.ABILITYSELECTION);
        selectedAbilityIcon.setSelected(false);
        EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_SELECTED_CHARACTER_CHANGED, selectedPlayer);
    }

    private void queueAbility(Unit source, Unit target, Ability ability) {
        if (combatPhase.equals(CombatPhase.TARGETSELECTION)) {
            combat.queuePlayerAbility(source, target, ability);
            changeCombatPhase(CombatPhase.ABILITYSELECTION);
            selectedAbilityIcon.setSelected(false);
            selectNextCharacter();
        }
    }

    private void changeCombatPhase(CombatPhase phase) {
        combatPhase = phase;
        EventBusImpl.getInstance().fireEvent(EventBusEvent.UI_COMBAT_COMBATPHASE_CHANGED, phase);
    }

    private void selectPreviousCharacter() {
        for (int i = combat.getPlayerGroup().size() - 1; i > 0; i--) {
            if (combat.getPlayerGroup().get(i).equals(selectedPlayer)) {
                selectCharacter(combat.getPlayerGroup().get(i - 1));
                break;
            }
        }
    }

    private void selectNextCharacter() {
        boolean found = false;
        for (int i = 0; i < combat.getPlayerGroup().size() - 1; i++) {
            if (combat.getPlayerGroup().get(i).equals(selectedPlayer)) {
                selectCharacter(combat.getPlayerGroup().get(i + 1));
                found = true;
                break;
            }
        }
        if (!found) {
            selectCharacter(selectedPlayer);
        }
    }

    private void DequeuePlayer() {
        combat.dequeuePlayerAbility(selectedPlayer);
        cancelQueue.setVisible(false);
    }

    private class UnitFrameListener implements MouseListener {

        private Unit unit;

        public UnitFrameListener(Unit unit) {
            this.unit = unit;
        }

        @Override
        public void onMouseMove(Vector2 position) {
        }

        @Override
        public void onMousePress(int button, Vector2 position) {
        }

        @Override
        public void onMouseRelease(int button, Vector2 position) {
            if (combatPhase.equals(CombatPhase.ABILITYSELECTION)) {
                selectCharacter(unit);
            }
        }

        @Override
        public void onMouseScroll(int amount) {
        }

    }

    private class TargetSelectionListener extends MouseAdapter {

        private Unit unit;

        public TargetSelectionListener(Unit unit) {
            this.unit = unit;
        }

        @Override
        public void onMouseRelease(int button, Vector2 position) {
            if (combatPhase.equals(CombatPhase.TARGETSELECTION)) {
                queueAbility(selectedPlayer, unit, selectedAbilityIcon.getAbility());
            }
        }

    }

    private class AbilityIconListener extends MouseAdapter {

        private AbilityIcon icon;

        public AbilityIconListener(AbilityIcon icon) {
            this.icon = icon;
        }

        @Override
        public void onMouseRelease(int button, Vector2 position) {
            abilityIconClick(icon);
        }

    }

    private class CancelQueueListener extends MouseAdapter {

        @Override
        public void onMouseRelease(int button, Vector2 position) {
            DequeuePlayer();
        }

    }

    private class LockButtonListener extends MouseAdapter {

        @Override
        public void onMouseRelease(int button, Vector2 position) {
            lockRound();
        }

    }

    private class KeyInput implements KeyListener {

        @Override
        public void onKeyUp(int keyCode) {
        }

        @Override
        public void onKeyDown(int keyCode) {
            System.out.println(keyCode);
            if (keyCode == KeyBindings.DOWN && combatPhase.equals(CombatPhase.ABILITYSELECTION)) {
                selectNextCharacter();
            }
            if (keyCode == KeyBindings.UP && combatPhase.equals(CombatPhase.ABILITYSELECTION)) {
                selectPreviousCharacter();
            }

            if (keyCode == KeyBindings.RIGHT && combatPhase.equals(CombatPhase.ABILITYSELECTION)) {
                selectNextAbilityIcon();
            }

            if (keyCode == KeyBindings.LEFT && combatPhase.equals(CombatPhase.ABILITYSELECTION)) {
                selectPreviousAbilityIcon();
            }

            if (keyCode == KeyBindings.ENTER) {
                switch (combatPhase) {
                    case TARGETSELECTION:
                        queueAbility(selectedPlayer, selectedTarget, selectedAbilityIcon.getAbility());
                        break;
                    case ABILITYSELECTION:
                        abilityIconClick(selectedAbilityIcon);
                        break;
                }
            }

            if (keyCode == KeyBindings.BACK) {
                switch (combatPhase) {
                    case TARGETSELECTION:
                        undoAbilityIconClick();
                        break;
                    case ABILITYSELECTION:
                        DequeuePlayer();
                        break;
                }
            }

            if (keyCode == KeyBindings.DOWN && combatPhase.equals(CombatPhase.TARGETSELECTION)) {
                selectNextTarget();
            }

            if (keyCode == KeyBindings.UP && combatPhase.equals(CombatPhase.TARGETSELECTION)) {
                selectPreviousTarget();
            }

            if ((keyCode == KeyBindings.LEFT || keyCode == KeyBindings.RIGHT) && combatPhase.equals(CombatPhase.TARGETSELECTION)) {
                changeTargetGroup();
            }

            if (keyCode == KeyBindings.START && combatPhase.equals(CombatPhase.ABILITYSELECTION)) {
                lockRound();
            }
        }

        @Override
        public void onKeytyped(char character) {
        }

    }
}
