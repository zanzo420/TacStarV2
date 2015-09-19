package com.tacstargame.ui.element;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.input.KeyEvent;
import com.tacstargame.input.MouseEvent;
import com.tacstargame.ui.UiContainer;
import com.tacstargame.ui.UiElement;
import com.tacstargame.ui.util.Measure;

public class UiContainerImpl extends UiElementImpl implements UiContainer {
	
	private List<UiElement> uiElements = new ArrayList<UiElement>();
	
	public UiContainerImpl(Vector2 position, Measure measure) {
		super(position, measure);
	}

	@Override
	public void render(SpriteBatch batch, Vector2 origin) {
		for (UiElement uiElement : uiElements) {
			uiElement.render(batch, origin.cpy().add(position));
		}
	}
	
	@Override
	public void mouseInput(MouseEvent event, int button, Vector2 position) {
		super.mouseInput(event, button, position);
		for (UiElement uiElement : uiElements) {
			uiElement.mouseInput(event, button, position.cpy().sub(getPosition()));
		}
	}
	
	@Override
	public void keyInput(KeyEvent event, int keyCode, char character) {
		super.keyInput(event, keyCode, character);
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
	public void scale(float scale) {
		super.scale(scale);
		for (UiElement uiElement : uiElements) {
			uiElement.scale(scale);
		}
	}

}
