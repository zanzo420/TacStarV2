package com.tacstargame.ui.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.combat.eventbus.EventBusEvent;
import com.tacstargame.combat.eventbus.EventBusImpl;
import com.tacstargame.combat.eventbus.EventBusListener;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.ui.element.FontRenderImpl;
import com.tacstargame.ui.element.UiElementImpl;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class Unitframe extends UiElementImpl implements EventBusListener {

    private Unit unit;

    private ResourceBar healthbar;
    private ResourceBar primaryResource;
    private SecondaryResourceBar secondaryResource;

    private boolean renderBorder = false;
    private Color borderColor = Color.GRAY;

    private final Measure barSize = new Measure(120, 30);
    private final int barBorderSize = 5;

    public Unitframe(Unit unit, Vector2 position, Measure measure) {
        super(position, measure);
        this.measure = new Measure(200, 200);
        this.unit = unit;
        healthbar = new ResourceBar(unit.getHealth(), position.cpy().add(0, barSize.height + barBorderSize), barSize);
        primaryResource = new ResourceBar(unit.getPrimaryResource(), position.cpy().add(0, 0), barSize);
        secondaryResource = new SecondaryResourceBar(unit, position, barSize);
        EventBusImpl.getInstance().registerForMultipleEvents(this, EventBusEvent.UI_COMBAT_SELECTED_CHARACTER_CHANGED, EventBusEvent.UI_COMBAT_SELECTED_TARGET_CHANGED);
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        renderBorder(batch);
        healthbar.render(batch, origin);
        primaryResource.render(batch, origin);
        secondaryResource.render(batch, origin);   
        FontRenderImpl.getInstance().renderText(batch, unit.getName(), origin.cpy().add(position).add(0, measure.height * 0.9f), Color.WHITE);
    }

    private void renderBorder(SpriteBatch batch) {
        if (renderBorder) {
            batch.end();
            shapeRender.setColor(borderColor);
            shapeRender.begin(ShapeRenderer.ShapeType.Filled);
            shapeRender.rect(position.x - barBorderSize - 1, position.y - barBorderSize - 1, barSize.width + barBorderSize * 2 + 2, (barSize.height + barBorderSize)*2 + barBorderSize  + 2);
            shapeRender.end();
            batch.begin();
        }
    }

    @Override
    public void dispose() {
        healthbar.dispose();
        primaryResource.dispose();
    }

    @Override
    public void update() {
        healthbar.update();
        primaryResource.update();
    }

    @Override
    public void scale(float scale) {
        super.scale(scale);
        healthbar.scale(scale);
        primaryResource.scale(scale);
        secondaryResource.scale(scale);
    }

    @Override
    public void OnEventFired(EventBusEvent busEvent, Object... args) {
        if (busEvent.equals(EventBusEvent.UI_COMBAT_SELECTED_CHARACTER_CHANGED)) {
            renderBorder = unit.equals(args[0]);
        }
        
        if (busEvent.equals(EventBusEvent.UI_COMBAT_SELECTED_TARGET_CHANGED)) {
            renderBorder = unit.equals(args[0]);
        }
    }

}
