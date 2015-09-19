package com.tacstargame.ui.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.ui.util.Measure;

public class Background extends UiElementImpl {

	private Texture texture;

	public Background(Texture texture) {
		super(new Vector2(0,0), new Measure(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		this.texture = texture;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		batch.draw(texture, position.x, position.y, measure.width, measure.height);
	}
	
	@Override
	public void scale(float scale) {
		setMeasure(new Measure(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}

	@Override
	public void dispose() {
		texture.dispose();		
	}

	@Override
	public void update() {}

	@Override
	public void render(SpriteBatch batch, Vector2 origin) {}



}
