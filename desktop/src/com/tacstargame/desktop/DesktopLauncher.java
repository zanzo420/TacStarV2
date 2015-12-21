package com.tacstargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tacstargame.TacStar;
import com.tacstargame.ui.Plattform;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 1600;
		config.height = 900;
		config.fullscreen = false;
		config.vSyncEnabled = false;
		config.foregroundFPS = 60;
		config.backgroundFPS = 30;
		Plattform plattform = Plattform.DESKTOP;
		config.resizable = false;
		new LwjglApplication(new TacStar(Plattform.DESKTOP), config);
	}
}
