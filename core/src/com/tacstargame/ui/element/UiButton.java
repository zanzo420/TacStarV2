package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.util.Measure;

public class UiButton extends UiElementImpl {
	
	private UiLabel label;
	
	public UiButton(String text, Vector2 position, Measure measure) {
		super(position, measure);
		label = new UiLabel(text, position);
	}

	@Override
	public void render(SpriteBatch batch, Vector2 origin) {
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void update() {
		
	}

}
