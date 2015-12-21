package com.tacstargame.ui.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.combat.unit.resource.Energy;
import com.tacstargame.combat.unit.resource.Health;
import com.tacstargame.combat.unit.resource.Mana;
import com.tacstargame.combat.unit.resource.Rage;
import com.tacstargame.combat.unit.resource.Resource;
import com.tacstargame.ui.element.FontRenderImpl;
import com.tacstargame.ui.element.UiElementImpl;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class ResourceBar extends UiElementImpl {

    private Resource resource;
    private Color barColor;
    private Color emptyBarColor = Color.GRAY;
    private Color borderColor = Color.BLACK;
    private int borderSize = 10;
    private int increase = 1;

    public ResourceBar(Resource resource, Vector2 position, Measure measure) {
        super(position, measure);
        this.resource = resource;
        barColor = getBarColor(resource);
    }

    private Color getBarColor(Resource resource) {
        if (resource instanceof Mana) {
            return Color.BLUE;
        }
        if (resource instanceof Rage) {
            return Color.RED;
        }
        if (resource instanceof Health) {
            return Color.GREEN;
        }
        if (resource instanceof Energy) {
            return Color.ORANGE;
        }
        return Color.WHITE;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        batch.end();
        shapeRender.setColor(borderColor);
        shapeRender.begin(ShapeRenderer.ShapeType.Filled);
        shapeRender.rect(origin.x + position.x - borderSize / 2, origin.y + position.y - borderSize / 2, measure.width + borderSize, measure.height + borderSize);
        shapeRender.setColor(emptyBarColor);
        shapeRender.rect(origin.x + position.x, origin.y + position.y, measure.width, measure.height);
        shapeRender.setColor(barColor);
        shapeRender.rect(origin.x + position.x, origin.y + position.y, measure.width * resource.getPercentage(), measure.height);
        shapeRender.end();
        batch.begin();
        FontRenderImpl.getInstance().renderSmallText(batch, resource.getCurrentValue() + "/" + resource.getMaxValue(), origin.cpy().add(position).add(measure.width * 0.05f, measure.height * 0.7f), Color.WHITE);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {
        //if (resource instanceof Health)
        //resource.increaseCurrentValue(increase);
        //if (resource.getCurrentValue() == resource.getMaxValue()) increase = -1;
        //if (resource.getCurrentValue() == 0) increase = 1;
    }

}
