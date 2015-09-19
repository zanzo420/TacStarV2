package com.tacstargame.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.input.InputListener;
import com.tacstargame.input.InputObservable;
import com.tacstargame.rendering.Renderable;
import com.tacstargame.rendering.Scaleable;

public interface UiElement extends Renderable, InputListener, InputObservable, Scaleable, ActionActor {
	boolean isMouseOver();
	void render(SpriteBatch batch, Vector2 origin);
	void setSelected(boolean selected);
	boolean isSelected();
}
