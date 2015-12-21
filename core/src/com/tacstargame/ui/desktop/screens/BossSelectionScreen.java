package com.tacstargame.ui.desktop.screens;

import com.tacstargame.TacStar;
import com.tacstargame.rendering.TextureResource;
import com.tacstargame.ui.element.Background;
import com.tacstargame.ui.screens.AbstractScreen;

/**
 *
 * @author Domenik Irrgang
 */
public class BossSelectionScreen extends AbstractScreen {

    public BossSelectionScreen(TacStar tacStar) {
        super(tacStar);
        background = new Background(TextureResource.get(TextureResource.TACSTAR_MAINMENUE_BACKGROUND));
    }
    
}
