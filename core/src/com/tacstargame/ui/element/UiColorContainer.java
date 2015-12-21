package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class UiColorContainer extends UiContainerImpl {
    
    private int borderSize = 6;
    private Color borderColor;
    private Color backgroundColor;
   

    public UiColorContainer(Color borderColor, Color backgroundColor, int borderSize, Vector2 position, Measure measure) {
        super(position, measure);
        this.borderSize = borderSize;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        batch.end();
        shapeRender.setColor(borderColor);
        shapeRender.begin(ShapeRenderer.ShapeType.Filled);
        shapeRender.rect(origin.x + position.x - borderSize / 2, origin.y + position.y - borderSize / 2, measure.width + borderSize, measure.height + borderSize);
        shapeRender.setColor(backgroundColor);
        shapeRender.rect(origin.x + position.x, origin.y + position.y, measure.width, measure.height);
        shapeRender.end();
        batch.begin();
        super.render(batch, origin);
    }
    
    
    
}
