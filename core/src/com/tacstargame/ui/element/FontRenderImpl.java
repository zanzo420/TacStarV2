/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacstargame.ui.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.FontRender;

/**
 *
 * @author Domenik Irrgang
 */
public class FontRenderImpl implements FontRender {

    private BitmapFont font;
    private BitmapFont bigFont;
    private int fontSize;
    private final int defaultFontSize = 20;
    private int bigFontSize;
    private final int defaultBigFontSize = 35;
    private final Color defaultColor = Color.WHITE;
    private final String fontPath;
    
    private static FontRender FONTRENDER = new FontRenderImpl("font.ttf");

    private FontRenderImpl(String fontPath) {
       /* if (FONTRENDER != null) {
            throw new IllegalStateException("FontRender is allready initiated!");
        }*/
        this.fontPath = fontPath;
    }
    /*
    public static FontRender getInstance() {
        return FONTRENDER;
    }*/
    
    public static FontRender getInstance() {
        return FONTRENDER;
    }

    private void renderFont(SpriteBatch batch, BitmapFont font, String text, Vector2 position, Color color) {
        if (font == null) { return; }
        if (color != null) {
            font.setColor(color);
        } else {
            font.setColor(defaultColor);
        }
        font.draw(batch, text, position.x, position.y);
    }

    @Override
    public void renderText(SpriteBatch batch, String text, Vector2 position, Color color) {
        renderFont(batch, font, text, position, color);
        System.out.println("Font: " + fontSize);
        System.out.println("BigFont: " + bigFontSize);
    }

    @Override
    public void renderBigText(SpriteBatch batch, String text, Vector2 position, Color color) {
        renderFont(batch, bigFont, text, position, color);
    }

    private void generateFonts(String fontPath, int fontSize, int bigFontSize) {
        if (this.fontSize != fontSize || this.bigFontSize != bigFontSize) {
            FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                    Gdx.files.internal("font.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter para = new FreeTypeFontGenerator.FreeTypeFontParameter();
            para.size = bigFontSize;
            bigFont = gen.generateFont(para);
            bigFont.setColor(defaultColor);

            para.size = fontSize;
            font = gen.generateFont(para);
            font.setColor(defaultColor);

            gen.dispose();

            this.fontSize = fontSize;
            this.bigFontSize = bigFontSize;
        }
    }

    @Override
    public void scale(float scale) {
        int fontSize = (int) (defaultFontSize * scale);
        int bigFontSize = (int) (defaultBigFontSize * scale);
        generateFonts(fontPath, fontSize, bigFontSize);
    }

}
