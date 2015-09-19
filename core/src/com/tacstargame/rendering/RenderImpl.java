package com.tacstargame.rendering;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.rendering.util.RenderSettings;
import com.tacstargame.ui.TacStarScreen;
import com.tacstargame.ui.element.FontRenderImpl;
import com.tacstargame.ui.element.UiElementImpl;
import com.tacstargame.ui.util.Measure;

public class RenderImpl implements Render {
	
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	
	private Vector2 maxResolution = new Vector2(1920, 1080);
	
	protected RenderSettings renderSettings;
	
	public RenderImpl(RenderSettings renderSettings) {
		batch = new SpriteBatch();
		setRenderSettings(renderSettings);
	}

	@Override
	public void render(TacStarScreen screen) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		renderBackground(screen);
		renderContent(screen);
		batch.end();
		renderLight(screen);
		batch.begin();
		renderUI(screen);
		batch.end();
	}

	@Override
	public void renderBackground(TacStarScreen screen) {
		if (renderSettings.isRenderBackground() && screen.getBackground() != null) {
			screen.getBackground().render(batch);
		}		
	}

	@Override
	public void renderLight(TacStarScreen screen) {
		if (renderSettings.isRenderLight() && screen.getRayHandler() != null) {
			screen.getPhysic().step(1/60f, 6, 2);
			screen.getRayHandler().setCombinedMatrix(camera.combined);
			screen.getRayHandler().updateAndRender();
		}
		
	}

	@Override
	public void renderUI(TacStarScreen screen) {
		if (renderSettings.isRenderUI() && screen.getUI() != null) {
			screen.getUI().render(batch);
		}
	}
	
	@Override
	public void renderContent(TacStarScreen screen) {
		
	}

	@Override
	public void dispose() {
		batch.dispose();		
	}

	@Override
	public RenderSettings getRenderSettings() {
		return renderSettings;
	}

	@Override
	public void setRenderSettings(RenderSettings renderSettings) {
		this.renderSettings = renderSettings;
		updateCamera();
                FontRenderImpl.getInstance().scale(getScale());
	}
	
	private void updateCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, renderSettings.getResolution().width, renderSettings.getResolution().height);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}
	
        @Override
	public float getScale() {
		return renderSettings.getResolution().width / maxResolution.x;
	}
	
	@Override
	public Vector2 getMaxResolution() {
		return maxResolution;
	}

	@Override
	public void setResolution(TacStarScreen tacStarScreen, int width,
			int height) {
		Gdx.graphics.setDisplayMode(width, height, renderSettings.isFullscreen());
		renderSettings.setResolution(new Measure(width, height));
		updateCamera();
                FontRenderImpl.getInstance().scale(getScale());
		UiElementImpl.shapeRender.setProjectionMatrix(camera.combined);
		tacStarScreen.scale(getScale());
	}

	@Override
	public List<Measure> getPossibleResolutions() {
		List<Measure> tmp = new ArrayList<Measure>();
		tmp.add(new Measure(1920, 1080));
		tmp.add(new Measure(1600, 900));
		tmp.add(new Measure(1280, 720));
		return tmp;
	}

	@Override
	public void setFullScreen(boolean fullScreen) {
		getRenderSettings().setFullscreen(fullScreen);
		Gdx.graphics.setDisplayMode(renderSettings.getResolution().width, renderSettings.getResolution().height, fullScreen);
	}

}
