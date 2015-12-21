package com.tacstargame.ui.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.combat.ability.Ability;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.element.FontRenderImpl;
import com.tacstargame.ui.element.UiElementImpl;
import static com.tacstargame.ui.element.UiElementImpl.shapeRender;
import com.tacstargame.ui.element.UiImage;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class AbilityIcon extends UiElementImpl implements EventBusListener {

    private Ability ability;
    private UiImage abilityIcon;
    private UiImage selectedOverlay;
    private UiImage cooldownOverlay;
    
    private boolean renderBorder = false;
    private int borderSize = 2;
    private Color borderColor = Color.WHITE;

    public AbilityIcon(Ability ability, Vector2 position, Measure measure) {
        super(position, measure);
        setAbility(ability);
        EventBusImpl.getInstance().registerForEvent(this, EventBusEvent.UI_COMBAT_SELECTED_ABILITY_CHANGED);
    }

    public final void setAbility(Ability ability) {
        this.ability = ability;
        if (ability != null) {
            this.abilityIcon = new UiImage(ability.getIcon(), position.cpy(), measure);
        } else {
            this.abilityIcon = new UiImage(TextureResource.get(TextureResource.ICON_ABILITY_EMPTYSLOT), position.cpy(), measure);
        }
        selectedOverlay = new UiImage(TextureResource.get(TextureResource.ICON_ABILITY_SELECTED_OVERLAY), position.cpy(), measure);
        cooldownOverlay = new UiImage(TextureResource.get(TextureResource.ICON_ABILITY_EMPTYSLOT), position.cpy(), measure);
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        if (visible) {
            renderBorder(batch);
            abilityIcon.render(batch, origin);
            if (ability != null) {
                if (ability.getRemainingCooldown() != 0) {
                    cooldownOverlay.render(batch, origin);
                    FontRenderImpl.getInstance().renderRealyBigText(batch, String.valueOf(ability.getRemainingCooldown()), position.cpy().add(measure.width / 2 - measure.width / 9, measure.height / 2 + measure.height / 5), Color.WHITE);
                } else {
                    if (selected || isMouseOver()) {
                        selectedOverlay.render(batch, origin);
                    }
                }

            }
        }
    }
    
    private void renderBorder(SpriteBatch batch) {
        if (renderBorder) {
            batch.end();
            shapeRender.setColor(borderColor);
            shapeRender.begin(ShapeRenderer.ShapeType.Filled);
            shapeRender.rect(position.x - borderSize, position.y - borderSize, measure.width + borderSize * 2, measure.height + borderSize * 2);
            shapeRender.end();
            batch.begin();
        }
    }
    
    public Ability getAbility() {
        return ability;
    }

    @Override
    public void dispose() {
        abilityIcon.dispose();
        selectedOverlay.dispose();
        cooldownOverlay.dispose();
    }

    @Override
    public void update() {
        abilityIcon.update();
        selectedOverlay.update();
        cooldownOverlay.update();
    }

    @Override
    public void scale(float scale) {
        super.scale(scale);
        abilityIcon.scale(scale);
        selectedOverlay.scale(scale);
        cooldownOverlay.scale(scale);
    }

    @Override
    public void OnEventFired(EventBusEvent busEvent, Object... args) {
        if (busEvent.equals(EventBusEvent.UI_COMBAT_SELECTED_ABILITY_CHANGED)) {
            renderBorder = args[0].equals(this);
        }
    }

}
