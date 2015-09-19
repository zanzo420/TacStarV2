package com.tacstargame.ui;

import box2dLight.RayHandler;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.tacstargame.rendering.Updateable;

public interface TacStarScreen extends Screen, Updateable {
	UiElement getBackground();
	Ui getUI();
	World getPhysic();
	RayHandler getRayHandler();
	void scale(float scale);
}
