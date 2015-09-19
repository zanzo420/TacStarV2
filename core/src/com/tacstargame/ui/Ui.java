package com.tacstargame.ui;

import com.badlogic.gdx.utils.Disposable;
import com.tacstargame.input.InputListener;
import com.tacstargame.rendering.RenderableCollection;
import com.tacstargame.rendering.Scaleable;
import com.tacstargame.rendering.Updateable;

public interface Ui extends InputListener, RenderableCollection, Disposable, Updateable, Scaleable {
	void addUiElement(UiElement uiElement);
	void removeUiElement(UiElement uiElement);
}
