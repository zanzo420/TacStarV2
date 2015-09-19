package com.tacstargame.ui.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.util.Measure;

public class UiFps extends UiElementImpl {

    public UiFps(Vector2 position, Measure measure) {
        super(position, measure);
    }

    @Override
    public void render(SpriteBatch batch, Vector2 origin) {
        if (visible) {
            System.out.println(position);
            FontRenderImpl.getInstance().renderBigText(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), origin.cpy().add(position), Color.RED);
            //font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), origin.cpy().add(getPosition()).x, origin.cpy().add(getPosition()).y);			
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void update() {
    }

}
