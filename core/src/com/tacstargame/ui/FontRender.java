/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    void renderText(SpriteBatch batch, String text, Vector2 position, Color color);
    void renderBigText(SpriteBatch batch, String text, Vector2 position, Color color);
}
