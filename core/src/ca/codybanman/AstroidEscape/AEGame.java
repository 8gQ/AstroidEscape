package ca.codybanman.AstroidEscape;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.AEHelpers.IActivityRequestHandler;
import ca.codybanman.GameServices.ActionResolver;
import ca.codybanman.Screens.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class AEGame extends Game implements ApplicationListener, ActionResolver {
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

	@Override
	public boolean getSignedInGPGS() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loginGPGS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void submitScoreGPGS(int score) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getLeaderboardGPGS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAchievementsGPGS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void signIn() {
		// TODO Auto-generated method stub

	}

}