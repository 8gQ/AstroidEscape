package ca.codybanman.AstroidEscape;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.Screens.GameScreen;

import com.badlogic.gdx.Game;


public class AEGame extends Game {

	@Override
	public void create() {
		System.out.println("AEGame Created!");
		AssetLoader.load();
		setScreen(new GameScreen());


	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}