package com.tacstargame.rendering;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteTween implements TweenAccessor<Sprite> {

	public static final int alpha = 1;
	
	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case alpha:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			return 0;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case alpha: 
			target.setColor(1, 1, 1, newValues[0]);
			break;
		}
	}
	

}
