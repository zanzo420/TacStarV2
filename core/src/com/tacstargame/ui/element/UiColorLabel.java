package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class UiColorLabel extends UiLabel {

    private Color color;

    public UiColorLabel(String text, Color color, Vector2 position) {
        super(text, position);
        this.color = color;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        if (mouseOver || selected) {
            FontRenderImpl.getInstance().renderBigText(batch, getText(), origin.cpy().add(getPosition()), color);
        } else {
            FontRenderImpl.getInstance().renderBigText(batch, getText(), origin.cpy().add(getPosition()), null);
        }

    }

}
