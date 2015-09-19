package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.util.Measure;

public class UiImage extends UiElementImpl {
	
	private Texture texture;

	public UiImage(Texture texture, Vector2 position, Measure measure) {
		super(position, measure);
		this.texture = texture;
	}

	@Override
	public void dispose() {
		texture.dispose();		
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void update() {}

	@Override
	public void render(SpriteBatch batch, Vector2 origin) {
		if (visible) { batch.draw(texture, origin.x + position.x, origin.y + position.y, measure.width, measure.height); }
	}

}
