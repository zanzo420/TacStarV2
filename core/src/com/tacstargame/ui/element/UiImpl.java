package com.tacstargame.ui.element;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.input.KeyEvent;
import com.tacstargame.input.MouseEvent;
import com.tacstargame.ui.Ui;
import com.tacstargame.ui.UiElement;

public class UiImpl implements Ui {
	
	private List<UiElement> uiElements = new ArrayList<UiElement>();
	
	@Override
	public void mouseInput(MouseEvent event, int button, Vector2 position) {
		for (UiElement uiElement : uiElements) {
			uiElement.mouseInput(event, button, position);
		}
	}
	
	@Override
	public void keyInput(KeyEvent event, int keyCode, char character) {
		for (UiElement uiElement : uiElements) {
			uiElement.keyInput(event, keyCode, character);
		}
	}

	@Override
	public void dispose() {
		for (UiElement uiElement : uiElements) {
			uiElement.dispose();
		}
	}

	@Override
	public void update() {
		for (UiElement uiElement : uiElements) {
			uiElement.update();
		}
	}

	@Override
	public void addUiElement(UiElement uiElement) {
		uiElements.add(uiElement);
	}

	@Override
	public void removeUiElement(UiElement uiElement) {
		uiElements.remove(uiElement);
	}

	@Override
	public void render(SpriteBatch batch) {
		for (UiElement uiElement : uiElements) {
			uiElement.render(batch, new Vector2(0,0));
		}
	}

	@Override
	public void scale(float scale) {
		for (UiElement uiElement : uiElements) {
			uiElement.scale(scale);
		}
	}

}
