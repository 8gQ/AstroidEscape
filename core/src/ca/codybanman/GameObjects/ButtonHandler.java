package ca.codybanman.GameObjects;

import java.util.ArrayList;
import java.util.List;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.GameWorld.GameWorld;
import ca.codybanman.ui.SimpleButton;

public class ButtonHandler {

	private List<SimpleButton> menuButtons;
	private SimpleButton playButton;
	private SimpleButton retryButton;
	private SimpleButton highScoreButton;

	public ButtonHandler(GameWorld myWorld) {
		menuButtons = new ArrayList<SimpleButton>();
		playButton = new SimpleButton(
				136 / 2 - (AssetLoader.retryButtonUp.getRegionWidth() / 2) - 14,
				myWorld.getMidPointY() + 5,
				84, 17, AssetLoader.playButtonUp,
				AssetLoader.playButtonDown);
		retryButton = new SimpleButton(
				136 / 2 - (AssetLoader.retryButtonUp.getRegionWidth() / 2) - 14,
				myWorld.getMidPointY() + 5, 84, 17, AssetLoader.retryButtonUp,
				AssetLoader.retryButtonDown);
		highScoreButton = new SimpleButton(
				136 / 2 - (AssetLoader.highScoreButtonUp.getRegionWidth() / 2) - 15,
				myWorld.getMidPointY() + 5 + 20, 84, 17,
				AssetLoader.highScoreButtonUp, AssetLoader.highScoreButtonDown);

		menuButtons.add(playButton);
		menuButtons.add(retryButton);
		menuButtons.add(highScoreButton);
	}

	public List<SimpleButton> getMenuButtons() {
		return menuButtons;
	}

	public SimpleButton getPlayButton() {
		return playButton;
	}

	public SimpleButton getRetryButton() {
		return retryButton;
	}

	public SimpleButton getHighScoreButton() {
		return highScoreButton;
	}

}