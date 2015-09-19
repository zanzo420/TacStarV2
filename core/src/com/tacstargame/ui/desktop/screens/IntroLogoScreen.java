package com.tacstargame.ui.desktop.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tacstargame.TacStar;
import com.tacstargame.rendering.SpriteTween;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.screens.AbstractScreen;
import com.tacstargame.ui.screens.AbstractTacStarScreenFactory;

public class IntroLogoScreen extends AbstractScreen {
	
	private Sprite fadingSprite;
	private SpriteBatch batch;
	private TweenManager manager;
	
	public IntroLogoScreen(TacStar tacStar) {
		super(tacStar);
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		System.out.println("test");
		batch.begin();
		fadingSprite.draw(batch);
		batch.end();
	}

	@Override
	public void show() {
		fadingSprite = new Sprite(TextureResource.get(TextureResource.TACSTAR_LOGO));
		fadingSprite.setSize(368, 200);
		fadingSprite.setX((Gdx.graphics.getWidth() / 2) - fadingSprite.getWidth() / 2);
		fadingSprite.setY((Gdx.graphics.getHeight() /2) - fadingSprite.getHeight() / 2);
		
		fadingSprite.setColor(1, 1, 1, 0);
		
		batch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());	
		manager = new TweenManager();	
		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};
		Tween.to(fadingSprite,  SpriteTween.alpha, 3f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 0.5f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	private void tweenCompleted() {
		tacStar.setScreen(AbstractTacStarScreenFactory.getTacStarScreenFactory(tacStar.getPlattform()).getMainMenue(tacStar));
	}



}
