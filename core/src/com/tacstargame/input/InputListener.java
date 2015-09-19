package com.tacstargame.input;

import com.badlogic.gdx.math.Vector2;

public interface InputListener {
	void keyInput(KeyEvent event, int keyCode, char character);
	void mouseInput(MouseEvent event, int button, Vector2 position);
}
 