package com.tacstargame.ui.combat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.element.UiElementImpl;
import com.tacstargame.ui.element.UiImage;
import com.tacstargame.ui.util.Measure;

/**
 *
 * @author Domenik Irrgang
 */
public class Tooltip extends UiElementImpl {
    
    private UiImage tooltipBackground;
    
    public Tooltip(Vector2 position, Measure measure) {
        super(position, measure);
        tooltipBackground  = new UiImage(TextureResource.get(TextureResource.ICON_ABILITY_EMPTYSLOT), position.cpy(), measure);
        tooltipBackground.setVisible(true);
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        tooltipBackground.render(batch, origin);
    }

    @Override
    public void dispose() {
        tooltipBackground.dispose();
    }

    @Override
    public void update() {
        tooltipBackground.update();
    }

    @Override
    public void scale(float scale) {
        super.scale(scale);
        tooltipBackground.scale(scale);
    }
    
}
