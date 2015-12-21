package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import static com.tacstargame.ui.element.UiElementImpl.shapeRender;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class UiBackgroundImageContainer extends UiContainerImpl {

    private Texture background;
    private float alpha;

    public UiBackgroundImageContainer(Texture background, float alpha, Vector2 position, Measure measure) {
        super(position, measure);
        this.background = background;
        this.alpha = alpha;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        float tmpAlpha = batch.getColor().a;
        batch.setColor(new Color(batch.getColor().r, batch.getColor().g, batch.getColor().b, alpha));
        batch.draw(background, origin.x + position.x, origin.y + position.y, measure.width, measure.height);     
        batch.setColor(new Color(batch.getColor().r, batch.getColor().g, batch.getColor().b, tmpAlpha));
        super.render(batch, origin);
    }

}
