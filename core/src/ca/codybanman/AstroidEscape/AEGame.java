package ca.codybanman.AstroidEscape;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.AEHelpers.IActivityRequestHandler;
import ca.codybanman.GameServices.ActionResolver;
import ca.codybanman.Screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class AEGame extends Game implements ApplicationListener {
	private IActivityRequestHandler myRequestHandler;
	private ActionResolver actionResolver;

    public AEGame(IActivityRequestHandler handler, ActionResolver actionResolver) {
        myRequestHandler = handler;
        this.actionResolver = actionResolver;
    }

	@Override
	public void create() {
		System.out.println("AEGame Created!");
		AssetLoader.load(myRequestHandler, actionResolver);
		setScreen(new GameScreen());

	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}