package com.tacstargame.ui.element;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.input.KeyBindings;
import com.tacstargame.input.KeyEvent;
import com.tacstargame.input.KeyListener;
import com.tacstargame.input.MouseEvent;
import com.tacstargame.input.MouseListener;
import com.tacstargame.ui.Action;
import com.tacstargame.ui.UiElement;
import com.tacstargame.ui.util.Measure;

public abstract class UiElementImpl implements UiElement {
	
	protected Vector2 originalPosition;
	protected Measure originalMeasure;
	protected Vector2 position;
	protected Measure measure;
	protected boolean visible;
	protected boolean mouseOver;
	protected boolean selected;
	
	protected static BitmapFont font;
	protected static BitmapFont smallFont;
	private static int fontSize;
	private static int smallFontSize;
	protected static Color defaultFontColor;
	
	public static ShapeRenderer shapeRender;
	
	protected List<MouseListener> mouseListener;
	protected List<KeyListener> keyListener;
	
	protected List<Action> actions;
	
	static {
		shapeRender = new ShapeRenderer();
		defaultFontColor = Color.WHITE;
		generateFonts(35, 20);
	}
	
	public UiElementImpl(Vector2 position, Measure measure) {
		this.position = (position != null) ? position : new Vector2(0,0);
		this.measure = (measure != null) ? measure : new Measure(0, 0);
		originalMeasure = new Measure(this.measure.width, this.measure.height);
		originalPosition = this.position.cpy();
		visible = true;
		selected = false;
		mouseListener = new ArrayList<MouseListener>();
		keyListener = new ArrayList<KeyListener>();
		actions = new ArrayList<Action>();
	}
	
	private static void generateFonts(int fontBig, int fontsmall) {
		if (fontBig != fontSize || fontsmall != smallFontSize) {
			FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
					Gdx.files.internal("font.ttf"));
			FreeTypeFontParameter para = new FreeTypeFontParameter();
			para.size = fontBig;
			font = gen.generateFont(para);
			font.setColor(defaultFontColor);
			
			para.size = fontsmall;
			smallFont = gen.generateFont(para);
			smallFont.setColor(defaultFontColor);
			
			gen.dispose();
			
			fontSize = fontBig;
			smallFontSize = fontsmall;
		}
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = (position != null) ? position : new Vector2(0,0);	
		originalPosition = this.position.cpy();
	}

	@Override
	public Measure getMeasure() {
		return measure;
	}

	@Override
	public void setMeasure(Measure measure) {
		this.measure = (measure != null) ? measure : new Measure(0, 0);
		
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;		
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void keyInput(KeyEvent event, int keyCode, char character) {
		for (KeyListener listener : keyListener) {
			switch (event) {
			case PRESS:
				if (visible)
				listener.onKeyDown(keyCode);
				break;
			case RELEASE:
				if (visible) {
					listener.onKeyUp(keyCode);
				}				
				break;
			case CHARTYPED:
				if (visible)
				listener.onKeytyped(character);
			}
		}
		if (selected && event.equals(KeyEvent.RELEASE) && KeyBindings.ENTER == keyCode) {
			doActions();
		}
		
	}

	@Override
	public void mouseInput(MouseEvent event, int button, Vector2 position) {
		mouseOver = isMouseOver(position);
		for (MouseListener listener : mouseListener) {
			switch (event) {
			case MOVE:
				if (mouseOver && visible) { listener.onMouseMove(position); }		
				break;
			case PRESS:
				if (mouseOver && visible) { listener.onMousePress(button, position); }
				break;
			case RELEASE:
				if (mouseOver && visible) {
					listener.onMouseRelease(button, position);
				}
				break;
			case SCROLL:
				if (mouseOver && visible) { listener.onMouseScroll(button); }				
				break;
			}
		}
		if (mouseOver && event.equals(MouseEvent.RELEASE)) {
			doActions();
		}
		
	}
	
	private void doActions() {
		if (visible) {
			for (Action action : actions) {
				action.action();
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if (!visible)
			return;
	}

	@Override
	public void addMouseListener(MouseListener listener) {
		mouseListener.add(listener);		
	}

	@Override
	public void addKeyListener(KeyListener listener) {
		keyListener.add(listener);
	}
	
	protected boolean isMouseOver(Vector2 position) {
		if (position.x >= getPosition().x &&
				position.x <= getPosition().x + getMeasure().width &&
				position.y >= getPosition().y &&
				position.y <= getPosition().y + getMeasure().height) {
			return true;
		}
		return false;
	}


	@Override
	public boolean isMouseOver() {
		return mouseOver;
	}

	@Override
	public void scale(float scale) {
		position.x = originalPosition.x * scale;
		position.y = originalPosition.y * scale;
		measure.width = (int) (originalMeasure.width * scale);
		measure.height = (int) (originalMeasure.height * scale);
		int fontSize = (int) (35 * scale);
		int smallFontSize =  (int) (20 * scale);
		generateFonts(fontSize, smallFontSize);
	}
	
	@Override
	public boolean isSelected() {
		return selected;
	}
	
	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public void addAction(Action action) {
		actions.add(action);
	}
	
	@Override
	public void removeAction(Action action) {
		actions.remove(action);
	}

}
