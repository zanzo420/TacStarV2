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
		
	}
}
