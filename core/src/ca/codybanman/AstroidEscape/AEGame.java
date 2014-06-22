package ca.codybanman.AstroidEscape;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.AEHelpers.IActivityRequestHandler;
import ca.codybanman.Screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class AEGame extends Game implements ApplicationListener {
	private IActivityRequestHandler myRequestHandler;

    public AEGame(IActivityRequestHandler handler) {
        myRequestHandler = handler;
    }

	@Override
	public void create() {
		System.out.println("AEGame Created!");
		AssetLoader.load(myRequestHandler);
		setScreen(new GameScreen());

	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}