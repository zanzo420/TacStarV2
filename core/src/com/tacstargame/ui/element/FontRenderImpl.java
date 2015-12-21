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

    private BitmapFont smallFont;
    private BitmapFont font;
    private BitmapFont bigFont;
    private BitmapFont realyBigFont;
    
    private int smallFontSize;
    private final int defaultSmallFontSize = 20;
    
    private int fontSize;
    private final int defaultFontSize = 27;
    
    private int bigFontSize;
    private final int defaultBigFontSize = 35;
    
    private int realyBigFontSize;
    private final int defaultRealyBigFontSize = 60;
    
    private final Color defaultColor = Color.WHITE;
    private final String fontPath;
    
    private static FontRender FONTRENDER = new FontRenderImpl("font.ttf");

    private FontRenderImpl(String fontPath) {
        this.fontPath = fontPath;
    }
    
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
    public void renderSmallText(SpriteBatch batch, String text, Vector2 position, Color color) {
        renderFont(batch, smallFont, text, position, color);
    }

    @Override
    public void renderText(SpriteBatch batch, String text, Vector2 position, Color color) {
        renderFont(batch, font, text, position, color);
    }

    @Override
    public void renderBigText(SpriteBatch batch, String text, Vector2 position, Color color) {
        renderFont(batch, bigFont, text, position, color);
    }
    
    @Override
    public void renderRealyBigText(SpriteBatch batch, String text, Vector2 position, Color color) {
        renderFont(batch, realyBigFont, text, position, color);
    }

    private void generateFonts(String fontPath, int smallFontSize, int fontSize, int bigFontSize, int realyBigFontSize) {
        if (this.fontSize != fontSize || this.bigFontSize != bigFontSize) {
            FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                    Gdx.files.internal("font.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter para = new FreeTypeFontGenerator.FreeTypeFontParameter();
            
            para.size = realyBigFontSize;
            realyBigFont = gen.generateFont(para);
            realyBigFont.setColor(defaultColor);
            
            para.size = bigFontSize;
            bigFont = gen.generateFont(para);
            bigFont.setColor(defaultColor);

            para.size = fontSize;
            font = gen.generateFont(para);
            font.setColor(defaultColor);
            
            para.size = smallFontSize;
            smallFont = gen.generateFont(para);
            smallFont.setColor(defaultColor);

            gen.dispose();

            this.fontSize = fontSize;
            this.bigFontSize = bigFontSize;
            this.smallFontSize = smallFontSize;
            this.realyBigFontSize = realyBigFontSize;
        }
    }

    @Override
    public void scale(float scale) {
        int fontSize = (int) (defaultFontSize * scale);
        int bigFontSize = (int) (defaultBigFontSize * scale);
        int smallFontSize = (int) (defaultSmallFontSize * scale);
        int realyBigFontSize = (int) (defaultRealyBigFontSize * scale);
        generateFonts(fontPath, smallFontSize, fontSize, bigFontSize, realyBigFontSize);
    }

}
