package com.tacstargame.ui.android;

import com.tacstargame.TacStar;
import com.tacstargame.ui.TacStarScreen;
import com.tacstargame.ui.screens.AbstractTacStarScreenFactory;

public class AndroidTacStarScreenFactory extends AbstractTacStarScreenFactory {

	@Override
	public TacStarScreen getMainMenue(TacStar tacStar) {
		return new MainMenue(tacStar);
	}

	@Override
	public TacStarScreen getIntroScreen(TacStar tacStar) {
		return null;
	}

	@Override
	public TacStarScreen getCombatScreen(TacStar tacStar) {
		return null;
	}
	
	@Override
	public TacStarScreen getSettingsScreen(TacStar tacStar) {
		return null;
	}

}
