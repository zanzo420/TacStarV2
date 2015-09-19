package com.tacstargame.rendering.util;

import com.tacstargame.ui.util.Measure;

public class RenderSettings {
	
	private boolean renderBackground = true;
	private boolean renderContent = true;
	private boolean renderLight = false;
	private boolean renderUI = true;
	
	private Measure resolution;
	private boolean fullscreen = false;
	
	public RenderSettings() {}
	
	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void setResolution(Measure resolution) {
		this.resolution = resolution;
	}
	
	public Measure getResolution() {
		return resolution;
	}

	public boolean isRenderBackground() {
		return renderBackground;
	}

	public void setRenderBackground(boolean renderBackground) {
		this.renderBackground = renderBackground;
	}

	public boolean isRenderContent() {
		return renderContent;
	}

	public void setRenderContent(boolean renderContent) {
		this.renderContent = renderContent;
	}

	public boolean isRenderLight() {
		return renderLight;
	}

	public void setRenderLight(boolean renderLight) {
		this.renderLight = renderLight;
	}

	public boolean isRenderUI() {
		return renderUI;
	}

	public void setRenderUI(boolean renderUI) {
		this.renderUI = renderUI;
	}
	
	
	
	
}
