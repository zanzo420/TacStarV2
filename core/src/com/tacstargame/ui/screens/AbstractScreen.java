package com.tacstargame.ui.screens;

import box2dLight.RayHandler;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.tacstargame.TacStar;
import com.tacstargame.ui.TacStarScreen;
import com.tacstargame.ui.Ui;
import com.tacstargame.ui.UiElement;
import com.tacstargame.ui.element.UiFps;
import com.tacstargame.ui.element.UiImpl;
import com.tacstargame.ui.element.UiMenu;
import com.tacstargame.ui.util.Measure;

public class AbstractScreen implements TacStarScreen {
	
	protected TacStar tacStar;
	
	protected Ui ui;
	protected UiElement background;
	protected World physic;
	protected RayHandler rayHandler;
	
	public AbstractScreen(TacStar tacStar) {
		this.tacStar = tacStar;
		ui = new UiImpl();
		physic = new World(new Vector2(0,0), true);
		rayHandler = new RayHandler(physic);
		rayHandler.setAmbientLight(1f);
		tacStar.getInput().clear();
		tacStar.getInput().addInputListener(ui);
		UiElement fpsCounter = new UiFps(new Vector2(1865, 1070), new Measure(0,0));
		UiMenu menu = new UiMenu(new Vector2(0,0), new Measure(0,0), 1);
		menu.addUiElement(0, fpsCounter);
		ui.addUiElement(menu);
		ui.scale(1);
	}
	
	@Override
	public void scale(float scale) {
		ui.scale(scale);
		if (background != null) { background.scale(scale); }
	}

	@Override
	public void render(float delta) {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}
	
	@Override
	public void show() {}
	
	@Override
	public void update() {
		ui.update();
		background.update();
	}

	@Override
	public void dispose() {
		background.dispose();		
		tacStar.getInput().removeInputListener(ui);
	}

	@Override
	public UiElement getBackground() {
		return background;
	}

	@Override
	public Ui getUI() {
		return ui;
	}

	@Override
	public World getPhysic() {
		return physic;
	}

	@Override
	public RayHandler getRayHandler() {
		return rayHandler;
	}	
	
}
