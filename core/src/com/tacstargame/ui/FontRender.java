package com.tacstargame.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.rendering.Scaleable;

/**
 *
 * @author Domenik Irrgang
 */
public interface FontRender extends Scaleable {
    void renderSmallText(SpriteBatch batch, String text, Vector2 position, Color color);
    void renderText(SpriteBatch batch, String text, Vector2 position, Color color);
    void renderBigText(SpriteBatch batch, String text, Vector2 position, Color color);
    void renderRealyBigText(SpriteBatch batch, String text, Vector2 position, Color color);
}
