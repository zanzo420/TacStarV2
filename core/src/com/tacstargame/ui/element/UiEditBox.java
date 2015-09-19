package com.tacstargame.ui.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tacstargame.input.KeyBindings;
import com.tacstargame.input.KeyListener;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.util.Measure;

public class UiEditBox extends UiElementImpl {
	
	private UiLabel label;
	private UiImage image;
	private String text;

	public UiEditBox(Vector2 position, Measure measure) {
		super(position, measure);
		label = new UiLabel("", new Vector2(0,0));
		text = "";
		image = new UiImage(TextureResource.get(TextureResource.UI_ELEMENT_EDITBOX), new Vector2(0,0), measure);
		addKeyListener(new EditBoxKeyListener());
	}

	@Override
	public void render(SpriteBatch batch, Vector2 origin) {
		image.render(batch, origin.cpy().add(position));
		label.render(batch, origin.cpy().add(position).add(10, label.getGlyphLayout().height + 10));
	}
	
	@Override
	public void scale(float scale) {
		super.scale(scale);
		image.scale(scale);
	}
	
	public String getText() {
		return text;
	}

	@Override
	public void dispose() {}

	@Override
	public void update() {}
	
	private class EditBoxKeyListener implements KeyListener {

		@Override
		public void onKeyDown(int keyCode) {
			if (selected) {
				if (keyCode == KeyBindings.BACK && text.length() != 0) {
					text = text.substring(0, text.length() - 1);
					updateText();
				}
				if (keyCode == KeyBindings.ENTER) {
					selected = false;
				}
			}	
		}

		@Override
		public void onKeyUp(int keyCode) {

		}
		
		private void updateText() {
			int i = 0;
			if (text != null) {
				label.setText(text);
				while(label.getGlyphLayout().width > measure.width - 10 && i < text.length()) {
					label.setText(text.substring(i, text.length()));
					i++;
				}
			}	
		}

		@Override
		public void onKeytyped(char character) {
			if (selected && character >= 65 && character <= 122) {
				text += character;
				updateText();
			}			
		}
		
		
	}

}
