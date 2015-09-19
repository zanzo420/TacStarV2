package com.tacstargame.rendering;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.tacstargame.rendering.util.RenderSettings;
import com.tacstargame.ui.TacStarScreen;
import com.tacstargame.ui.util.Measure;

public interface Render extends Disposable {
	
	void render(TacStarScreen screen);
	void renderBackground(TacStarScreen screen);
	void renderLight(TacStarScreen screen);
	void renderUI(TacStarScreen screen);
	void renderContent(TacStarScreen screen);
	RenderSettings getRenderSettings();
	void setRenderSettings(RenderSettings renderSettings);
	void setResolution(TacStarScreen tacStarScreen, int width, int height);
	void setFullScreen(boolean fullScreen);
	float getScale();
	Vector2 getMaxResolution();
	List<Measure> getPossibleResolutions();
}
