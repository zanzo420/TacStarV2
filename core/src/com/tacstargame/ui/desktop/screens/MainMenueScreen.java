package com.tacstargame.ui.desktop.screens;

import box2dLight.PointLight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.TacStar;
import com.tacstargame.input.KeyBindings;
import com.tacstargame.input.KeyListener;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.Action;
import com.tacstargame.ui.element.Background;
import com.tacstargame.ui.element.UiColorLabel;
import com.tacstargame.ui.element.UiEditBox;
import com.tacstargame.ui.element.UiImage;
import com.tacstargame.ui.element.UiMenu;
import com.tacstargame.ui.screens.AbstractScreen;
import com.tacstargame.ui.screens.AbstractTacStarScreenFactory;
import com.tacstargame.ui.util.Measure;

public class MainMenueScreen extends AbstractScreen {
	
	private UiColorLabel singleplayer;
	private UiColorLabel settings;
	private UiColorLabel exit;
	private UiMenu menu;
	
	private UiEditBox editbox;
	
	private UiImage logo;

	public MainMenueScreen(TacStar tacStar) {
		super(tacStar);	
		background = new Background(TextureResource.get(TextureResource.TACSTAR_MAINMENUE_BACKGROUND));
		logo = new UiImage(TextureResource.get(TextureResource.TACSTAR_LOGO),
							new Vector2(1920f / 2 - 150, 1080f * 0.65f),
							new Measure(300,150));
		ui.addUiElement(logo);
		
		new PointLight(rayHandler, 1000, Color.NAVY, 500, 600, 500);
		
		menu = new UiMenu(null, null, 3);
		menu.setSelected(true);
		ui.addUiElement(menu);
		singleplayer = new UiColorLabel("Singleplayer", Color.RED, new Vector2(0,0));
		singleplayer.setPosition(new Vector2((1920f / 2) - (singleplayer.getGlyphLayout().width / 2) + 4, 1080f * 0.4f));
		settings = new UiColorLabel("Settings", Color.RED, new Vector2(0,0));
		settings.setPosition(new Vector2((1920f / 2) - (settings.getGlyphLayout().width / 2) + 4, 1080f * 0.35f));
		exit = new UiColorLabel("Exit", Color.RED, new Vector2(0,0));
		exit.setPosition(new Vector2((1920f / 2) - (exit.getGlyphLayout().width / 2) + 4, 1080f * 0.3f));
		exit.addAction(new ExitAction());
		settings.addAction(new SettingsAction());
		menu.addUiElement(0, singleplayer);
		menu.addUiElement(1, settings);
		menu.addUiElement(2, exit);
		
		menu.addKeyListener(new SearchKeyListener());
		
		editbox = new UiEditBox(new Vector2(400,400), new Measure(250,50));
		menu.addUiElement(editbox);
	}
	
	private class SearchKeyListener implements KeyListener {

		@Override
		public void onKeyDown(int keyCode) {
			if (keyCode == KeyBindings.ENTER) {
				menu.setSelected(true);
			}
		}

		@Override
		public void onKeyUp(int keyCode) {}

		@Override
		public void onKeytyped(char character) {
			if (character == 'f') {
				menu.setSelected(false);
				editbox.setSelected(true);
			}
		}
		
	}
	
	private class ExitAction implements Action {

		@Override
		public void action() {
			Gdx.app.exit();
		}
		
	}
	
	private class SettingsAction implements Action {

		@Override
		public void action() {
			tacStar.setScreen(AbstractTacStarScreenFactory.getTacStarScreenFactory(tacStar.getPlattform()).getSettingsScreen(tacStar));	
		}
		
	}

}
