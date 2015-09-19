package com.tacstargame.input;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Input implements InputProcessor, ControllerListener {
	
	private List<InputListener> inputListener = new ArrayList<InputListener>();
	
	public Input() {
		Controllers.addListener(this);
	}
	
	public void addInputListener(InputListener inputListener) {
		this.inputListener.add(inputListener);
	}
	
	public void removeInputListener(InputListener inputListener) {
		this.inputListener.remove(inputListener);
	}
	
	public void clear() {
		inputListener.clear();
	}

	@Override
	public boolean keyDown(int keycode) {
		for (InputListener listener : inputListener) {
			listener.keyInput(KeyEvent.PRESS, keycode, '\n');
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for (InputListener listener : inputListener) {
			listener.keyInput(KeyEvent.RELEASE, keycode, '\n');
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for (InputListener listener : inputListener) {
			listener.keyInput(KeyEvent.CHARTYPED, 0, character);
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for (InputListener listener : inputListener) {
			listener.mouseInput(MouseEvent.PRESS, button, invertMouse(new Vector2(screenX, screenY)));
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for (InputListener listener : inputListener) {
			listener.mouseInput(MouseEvent.RELEASE, button, invertMouse(new Vector2(screenX, screenY)));
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		for (InputListener listener : inputListener) {
			listener.mouseInput(MouseEvent.MOVE, -1, invertMouse(new Vector2(screenX, screenY)));
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		for (InputListener listener : inputListener) {
			listener.mouseInput(MouseEvent.SCROLL, amount, invertMouse(new Vector2(Gdx.input.getX(), Gdx.input.getY())));
		}
		return false;
	}
	
	private Vector2 invertMouse(Vector2 position) {
		return new Vector2(position.x, Gdx.graphics.getHeight() - position.y);
	}

	@Override
	public void connected(Controller controller) {

	}

	@Override
	public void disconnected(Controller controller) {	
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		switch (buttonCode) {
		case 0:
			keyUp(KeyBindings.ENTER);
			break;
		case 1:
			keyUp(KeyBindings.BACK);
			break;
		case 7:
			keyUp(KeyBindings.ESC);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode,
			PovDirection value) {
		switch (value) {
		case east:
			keyUp(KeyBindings.RIGHT);
			break;
		case north:
			keyUp(KeyBindings.UP);
			break;
		case west:
			keyUp(KeyBindings.LEFT);
			break;
		case south:
			keyUp(KeyBindings.DOWN);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode,
			boolean value) {
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode,
			boolean value) {
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller,
			int accelerometerCode, Vector3 value) {
		return false;
	}

}
