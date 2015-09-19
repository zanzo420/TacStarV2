package com.tacstargame.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.tacstargame.ui.util.Measure;

public interface Renderable extends Disposable, Updateable {
	void render(SpriteBatch batch);
	Vector2 getPosition();
	void setPosition(Vector2 position);
	Measure getMeasure();
	void setMeasure(Measure measure);
	void setVisible(boolean visible);
	boolean isVisible();
}
