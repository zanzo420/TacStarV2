package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.util.Measure;

public class UiButton extends UiElementImpl {

    private final UiImage button;
    private final UiImage buttonMouseOver;
    private final UiImage buttonMousePress;

    public UiButton(Vector2 position, Measure measure) {
        super(position, measure);
        button = new UiImage(TextureResource.get(TextureResource.COMBAT_BUTTON_LOCK), position.cpy(), measure);
        buttonMouseOver = new UiImage(TextureResource.get(TextureResource.COMBAT_BUTTON_LOCK_MOUSEOVER), position.cpy(), measure);
        buttonMousePress = new UiImage(TextureResource.get(TextureResource.COMBAT_BUTTON_LOCK_PRESSED), position.cpy(), measure);
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        if (mousePress) {
            buttonMousePress.render(batch, origin);
        } else {
            button.render(batch, origin);
            if (mouseOver) {
                buttonMouseOver.render(batch, origin);
            }
        }
    }

    @Override
    public void dispose() {
        button.dispose();
        buttonMouseOver.dispose();
        buttonMousePress.dispose();
    }

    @Override
    public void update() {
        button.update();
        buttonMouseOver.update();
        buttonMousePress.update();
    }

    @Override
    public void scale(float scale) {
        super.scale(scale);
        button.scale(scale);
        buttonMouseOver.scale(scale);
        buttonMousePress.scale(scale);
    }

}
