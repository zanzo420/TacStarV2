package com.tacstargame.ui.desktop.screens;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.TacStar;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.Action;
import com.tacstargame.ui.element.Background;
import com.tacstargame.ui.element.UiColorLabel;
import com.tacstargame.ui.element.UiMenu;
import com.tacstargame.ui.screens.AbstractScreen;
import com.tacstargame.ui.screens.AbstractTacStarScreenFactory;
import com.tacstargame.ui.util.Measure;

public class SettingsScreen extends AbstractScreen {
	
	private UiMenu menu;
	private UiColorLabel resolution;
	private UiColorLabel fullscreen;
	private UiColorLabel back;
	
	private List<Measure> possibleResolutions;

	public SettingsScreen(TacStar tacStar) {
		super(tacStar);
		background = new Background(TextureResource.get(TextureResource.TACSTAR_MAINMENUE_BACKGROUND));
		possibleResolutions = tacStar.getRender().getPossibleResolutions();
		
		menu = new UiMenu(null, null, 3);
		menu.setSelected(true);
		ui.addUiElement(menu);
		
		resolution = new UiColorLabel("Resolution: " + tacStar.getRender().getRenderSettings().getResolution().toString(), Color.CYAN, new Vector2(0,0));
		resolution.setPosition(new Vector2((1920f / 2) - (resolution.getGlyphLayout().width / 2) + 4, 1080f * 0.4f));
		resolution.setSelected(true);
		resolution.addAction(new ChangeResolutionAction());
		
		fullscreen = new UiColorLabel(tacStar.getRender().getRenderSettings().isFullscreen() ? "Fullscreen: ON" : "Fullscreen: OFF", Color.CYAN, new Vector2(0,0));
		fullscreen.setPosition(new Vector2((1920f / 2) - (fullscreen.getGlyphLayout().width / 2) + 4, 1080f * 0.35f));
		fullscreen.addAction(new ChangeFullScreenAction());
		
		back = new UiColorLabel("Back", Color.CYAN, new Vector2(0,0));
		back.setPosition(new Vector2((1920f / 2) - (back.getGlyphLayout().width / 2) + 4, 1080f * 0.3f));
		back.addAction(new BackAction());
		
		menu.addUiElement(0, resolution);
		menu.addUiElement(1, fullscreen);
		menu.addUiElement(2, back);
	}
	
	private class ChangeResolutionAction implements Action {
		
		@Override
		public void action() {
			int i = findCurrentResolution();
			if (i >= possibleResolutions.size() - 1) { i = 0; } else { i++; }
			Measure tmp = possibleResolutions.get(i);
			tacStar.getRender().setResolution(SettingsScreen.this, tmp.width, tmp.height);
			resolution.setText("Resolution: " + tmp.toString());
		}
		
		private int findCurrentResolution() {
			for (int i = 0; i < possibleResolutions.size(); i++) {
				if (possibleResolutions.get(i).equals(tacStar.getRender().getRenderSettings().getResolution())) {
					return i;
				}
			}
			return -1;
		}
		
	}
	
	private class ChangeFullScreenAction implements Action {

		@Override
		public void action() {
			tacStar.getRender().setFullScreen(!tacStar.getRender().getRenderSettings().isFullscreen());		
			fullscreen.setText((tacStar.getRender().getRenderSettings().isFullscreen()) ? "Fullscreen: ON" : "Fullscreen: OFF");
		}
		
	}
	
	private class BackAction implements Action {

		@Override
		public void action() {
			tacStar.setScreen(AbstractTacStarScreenFactory.getTacStarScreenFactory(tacStar.getPlattform()).getMainMenue(tacStar));
		}
		
	}

}
