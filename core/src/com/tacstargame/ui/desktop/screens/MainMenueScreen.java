package com.tacstargame.ui.desktop.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.TacStar;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.Action;
import com.tacstargame.ui.element.Background;
import com.tacstargame.ui.element.UiColorLabel;
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
    
    private UiImage logo;
    
    public MainMenueScreen(TacStar tacStar) {
        super(tacStar);        
        background = new Background(TextureResource.get(TextureResource.TACSTAR_MAINMENUE_BACKGROUND)); 
        logo = new UiImage(TextureResource.get(TextureResource.TACSTAR_LOGO),
                new Vector2(1920f / 2 - 233, 1080f * 0.65f),
                new Measure(466, 231));
        ui.addUiElement(logo);
        menu = new UiMenu(null, null, 3);
        menu.setSelected(true);
        ui.addUiElement(menu);
        singleplayer = new UiColorLabel("Singleplayer", Color.RED, new Vector2(0, 0));
        singleplayer.setPosition(new Vector2((1920f / 2) - (singleplayer.getGlyphLayout().width / 2) + 4, 1080f * 0.4f));
        settings = new UiColorLabel("Settings", Color.RED, new Vector2(0, 0));
        settings.setPosition(new Vector2((1920f / 2) - (settings.getGlyphLayout().width / 2) + 4, 1080f * 0.35f));
        exit = new UiColorLabel("Exit", Color.RED, new Vector2(0, 0));
        exit.setPosition(new Vector2((1920f / 2) - (exit.getGlyphLayout().width / 2) + 4, 1080f * 0.3f));
        exit.addAction(new ExitAction());
        settings.addAction(new SettingsAction());
        singleplayer.addAction(new SinglePlayerAction());
        menu.addUiElement(0, singleplayer);
        menu.addUiElement(1, settings);
        menu.addUiElement(2, exit);
    }
    
    private class SinglePlayerAction implements Action {
        
        @Override
        public void action() {
            tacStar.setScreen(AbstractTacStarScreenFactory.getTacStarScreenFactory(tacStar.getPlattform()).getCombatScreen(tacStar));
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
