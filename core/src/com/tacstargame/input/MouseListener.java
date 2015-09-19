package com.tacstargame.input;

import com.badlogic.gdx.math.Vector2;

public interface MouseListener {
	void onMouseMove(Vector2 position);
	void onMousePress(int button, Vector2 position);
	void onMouseRelease(int button, Vector2 position);
	void onMouseScroll(int amount);
}
