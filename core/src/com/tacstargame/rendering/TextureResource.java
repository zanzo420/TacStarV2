package com.tacstargame.rendering;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class TextureResource {
private static AssetManager manager;
	
	public static final String path = "data/";	
	public static final String TACSTAR_LOGO = "Logo.png";
	public static final String STUDIO_LOGO = "Logo.png";
	public static final String TACSTAR_MAINMENUE_BACKGROUND = "mainmenue_background.png";
	public static final String UI_ELEMENT_EDITBOX = "editbox.png";
        public static final String COMBAT_BACKGROUND_WHITE = "background_white.png";
        public static final String COMBAT_COMBOPOINT_FILLED = "combat_combopoint_filled.png";
        public static final String COMBAT_COMBOPOINT_EMPTY = "combat_combopoint_empty.png";
        public static final String COMBAT_ABILITYQUEUE_QUEUED = "combat_abilityqueue_queued.png";
        public static final String COMBAT_ABILITYQUEUE_NOTQUEUED = "combat_abilityqueue_notqueued.png";
        public static final String ICON_ABILITY_MINDBLAST = "icon_ability_mindblast.jpg";
        public static final String ICON_ABILITY_EMPTYSLOT = "icon_ability_emptyslot.png";
        public static final String ICON_ABILITY_RENEW = "icon_ability_renew.jpg";
        public static final String ICON_ABILITY_SELECTED_OVERLAY = "icon_ability_selected_overlay.png";
        public static final String COMBAT_BUTTON_LOCK = "combat_button_lock.png";
        public static final String COMBAT_BUTTON_LOCK_PRESSED = "combat_button_lock_pressed.png";
        public static final String COMBAT_BUTTON_LOCK_MOUSEOVER = "combat_button_lock_mouseover.png";

	
	
	public static Texture get(String id) {
		manager.finishLoading();
		return manager.get(id, Texture.class);
	}
	
	public static void load() {
		manager = new AssetManager();
		
		TextureParameter parameter = new TextureParameter();
		parameter.minFilter = TextureFilter.Linear;
		parameter.magFilter = TextureFilter.Linear;
		parameter.genMipMaps = true;

		manager.load(TACSTAR_LOGO, Texture.class, parameter);
		manager.load(TACSTAR_MAINMENUE_BACKGROUND, Texture.class, parameter);
		manager.load(STUDIO_LOGO, Texture.class, parameter);
		manager.load(UI_ELEMENT_EDITBOX, Texture.class, parameter);
                manager.load(COMBAT_BACKGROUND_WHITE, Texture.class, parameter);
		manager.load(COMBAT_COMBOPOINT_EMPTY, Texture.class, parameter);
                manager.load(COMBAT_COMBOPOINT_FILLED, Texture.class, parameter);
                manager.load(COMBAT_ABILITYQUEUE_QUEUED, Texture.class, parameter);
                manager.load(COMBAT_ABILITYQUEUE_NOTQUEUED, Texture.class, parameter);
                manager.load(ICON_ABILITY_MINDBLAST, Texture.class, parameter);
                manager.load(ICON_ABILITY_EMPTYSLOT, Texture.class, parameter);
                manager.load(ICON_ABILITY_RENEW, Texture.class, parameter);
                manager.load(ICON_ABILITY_SELECTED_OVERLAY, Texture.class, parameter);
                manager.load(COMBAT_BUTTON_LOCK, Texture.class, parameter);
                manager.load(COMBAT_BUTTON_LOCK_PRESSED, Texture.class, parameter);
                manager.load(COMBAT_BUTTON_LOCK_MOUSEOVER, Texture.class, parameter);
	}
}
