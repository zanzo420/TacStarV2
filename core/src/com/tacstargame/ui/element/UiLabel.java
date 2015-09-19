package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.util.Measure;

public class UiLabel extends UiElementImpl {
	 
	private GlyphLayout glyphLayout;
	private String text;

	public UiLabel(String text, Vector2 position) {
		super(position, new Measure(0,0));
		setText(text);
	}
	
	public void setText(String text) {
		this.text = text;
		glyphLayout = new GlyphLayout(font, text);
		measure.width = (int) glyphLayout.width;
		measure.height = (int) glyphLayout.height;
	}
	
	public String getText() {
		return text;
	}
	
	public GlyphLayout getGlyphLayout() {
		return glyphLayout;
	}
	
	@Override
	protected boolean isMouseOver(Vector2 position) {
		if (position.x >= getPosition().x &&
				position.x <= getPosition().x + getMeasure().width &&
				position.y >= getPosition().y - getMeasure().height &&
				position.y <= getPosition().y) {
			return true;
		}
		return false;
	}
	
	@Override
	public void scale(float scale) {
		super.scale(scale);
		setText(text);
	}

	@Override
	public void render(SpriteBatch batch, Vector2 origin) {
		if (visible) {
			//font.draw(batch, glyphLayout, origin.cpy().add(getPosition()).x, origin.cpy().add(getPosition()).y);
                        FontRenderImpl.getInstance().renderBigText(batch, text, origin.cpy().add(getPosition()), null);
		}
	}

	@Override
	public void dispose() {}

	@Override
	public void update() {}

}
