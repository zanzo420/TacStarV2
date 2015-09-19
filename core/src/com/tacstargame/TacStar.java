package com.tacstargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tacstargame.input.Input;
import com.tacstargame.rendering.Render;
import com.tacstargame.rendering.RenderImpl;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.rendering.util.RenderSettings;
import com.tacstargame.ui.Plattform;
import com.tacstargame.ui.TacStarScreen;
import com.tacstargame.ui.screens.AbstractTacStarScreenFactory;
import com.tacstargame.ui.util.Measure;

public class TacStar extends Game {
	
	public static final String version = "0.0.1 Pre-Alpha TacStar";
	
	private Plattform plattform;
	private Render render;
	private Input input;;
	
	private TacStarScreen tacStarScreen;
	
	public TacStar(Plattform plattform) {
		this.plattform = plattform;		
		TextureResource.load();
	}
	
	public Plattform getPlattform() {
		return plattform;
	}
	
	public Input getInput() {
		return input;
	}
	
	public Render getRender() {
		return render;
	}
	
	@Override
	public void setScreen(Screen screen) {
		tacStarScreen = (TacStarScreen) screen;
		tacStarScreen.getUI().scale(render.getScale());
	}
	
	@Override
	public void create () {
		input = new Input();
		RenderSettings renderSettings = new RenderSettings();
		renderSettings.setResolution(new Measure(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		render = new RenderImpl(renderSettings);
		Gdx.input.setInputProcessor(input);
		setScreen(AbstractTacStarScreenFactory.getTacStarScreenFactory(plattform).getMainMenue(this));
	}

	@Override
	public void render () {
		render.render(tacStarScreen);
		tacStarScreen.update();
	}
}
