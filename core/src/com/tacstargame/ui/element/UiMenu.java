package com.tacstargame.ui.element;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.input.KeyBindings;
import com.tacstargame.input.KeyListener;
import com.tacstargame.ui.UiElement;
import com.tacstargame.ui.util.Measure;

public class UiMenu extends UiContainerImpl {

	private List<List<UiElement>> uiElement;
	private int selectedRow;
	private int selectedCol;
	
	public UiMenu(Vector2 position, Measure measure, int rowCount) {
		super(position, measure);
		uiElement = new ArrayList<List<UiElement>>();
		setUpList(rowCount);
		selectedRow = 0;
		selectedCol = 0;
		addKeyListener(new MenuKeyListener());
	}
	
	public void addUiElement(int row, UiElement uiElement) {
		this.uiElement.get(row).add(uiElement);
		addUiElement(uiElement);
	}
	
	private void setUpList(int length) {
		for (int i = 0; i < length; i++) {
			uiElement.add(new ArrayList<UiElement>());
		}
	}
	
	public UiElement getSelectedElement() {
		return uiElement.get(selectedRow).get(selectedCol);
	}
	
	private void goUp() {
		if (selectedRow <= 0) {
			selectedRow = uiElement.size() - 1;
		} else {
			selectedRow--;
		}
		selectedCol = uiElement.get(selectedRow).size() - 1;	
	}
	
	private void goDown() {
		if (selectedRow >= uiElement.size() - 1) {
			selectedRow = 0;
		} else {
			selectedRow++;
		}
		selectedCol = 0;
	}
	
	private void goRight() {
		if (selectedCol >= uiElement.get(selectedRow).size() - 1) {
			goDown();
		} else {
			selectedCol++;
		}
	}
	
	private void goLeft() {
		if (selectedCol <= 0) {
			goUp();					
		} else {
			selectedCol--;
		}
	}
	
	private class MenuKeyListener implements KeyListener {

		@Override
		public void onKeyDown(int keyCode) {
			
		}

		@Override
		public void onKeyUp(int keyCode) {
			if (selected && KeyBindings.RIGHT == keyCode) {
				uiElement.get(selectedRow).get(selectedCol).setSelected(false);
				goRight();
				uiElement.get(selectedRow).get(selectedCol).setSelected(true);
			}
			
			if (selected && KeyBindings.DOWN == keyCode) {
				uiElement.get(selectedRow).get(selectedCol).setSelected(false);
				goDown();
				uiElement.get(selectedRow).get(selectedCol).setSelected(true);
			}
			
			if (selected && KeyBindings.LEFT == keyCode) {
				uiElement.get(selectedRow).get(selectedCol).setSelected(false);
				goLeft();
				uiElement.get(selectedRow).get(selectedCol).setSelected(true);
			}
			
			if (selected && KeyBindings.UP == keyCode) {
				uiElement.get(selectedRow).get(selectedCol).setSelected(false);
				goUp();
				uiElement.get(selectedRow).get(selectedCol).setSelected(true);
			}
		}

		@Override
		public void onKeytyped(char character) {}
		
	}

}
