package com.tacstargame.ui.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.combat.unit.Unit;
import com.tacstargame.combat.unit.resource.ComboPoints;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.element.UiElementImpl;
import com.tacstargame.ui.element.UiImage;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class SecondaryResourceBar extends UiElementImpl {

    private final Unit unit;
    private final UiImage resourceFilled;
    private final UiImage resourceEmpty;
    private int maxComboPoints;

    public SecondaryResourceBar(Unit unit, Vector2 position, Measure measure) {
        super(position, measure);
        this.unit = unit;
        
        Texture filled = null;
        Texture empty = null;
        
        if (unit.getSecondaryResource().getClass().equals(ComboPoints.class)) {
            filled = TextureResource.get(TextureResource.COMBAT_COMBOPOINT_FILLED);
            empty = TextureResource.get(TextureResource.COMBAT_COMBOPOINT_EMPTY);
        }
        resourceFilled = new UiImage(filled, position.cpy().add(0, -40), new Measure(30, 30));
        resourceEmpty = new UiImage(empty, position.cpy().add(0, -40), new Measure(30, 30));
    }
    

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        for (int i = 1; i <= unit.getSecondaryResource().getMaxValue(); i++) {
            drawResource(batch, origin, i);
        }
    }

    private void drawResource(SpriteBatch batch, Vector2 origin, int count) {
        if (unit.getSecondaryResource().getCurrentValue() >= count) {
            resourceFilled.setPosition(resourceFilled.getPosition().set(origin.cpy().add(position.x, 0).x +(count - 1) * measure.width / unit.getSecondaryResource().getMaxValue(), resourceFilled.getPosition().y));
            resourceFilled.render(batch, origin);
        } else {
            resourceEmpty.setPosition(resourceEmpty.getPosition().set(origin.cpy().add(position.x, 0).x +(count - 1) * measure.width / unit.getSecondaryResource().getMaxValue(), resourceFilled.getPosition().y));
            resourceEmpty.render(batch, origin);
        }
    }

    @Override
    public void scale(float scale) {
        super.scale(scale);
        resourceEmpty.scale(scale);
        resourceFilled.scale(scale);
    }

    @Override
    public void dispose() {
        resourceEmpty.dispose();
        resourceFilled.dispose();
    }

    @Override
    public void update() {
        resourceEmpty.update();
        resourceFilled.update();
    }

}
