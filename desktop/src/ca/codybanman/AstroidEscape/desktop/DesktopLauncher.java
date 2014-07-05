package ca.codybanman.AstroidEscape.desktop;

import ca.codybanman.AEHelpers.IActivityRequestHandler;
import ca.codybanman.AstroidEscape.AEGame;
import ca.codybanman.GameServices.ActionResolver;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher implements IActivityRequestHandler, ActionResolver{
	public static void main(String[] args) {
		
		DesktopLauncher application = null;
		if (application == null) {
            application = new DesktopLauncher();
        }
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Astroid Escape";
		cfg.width = 272*2;
		cfg.height = 408*2;
		
		new LwjglApplication(new AEGame(application, application), cfg);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void postToFacebook(double score) {
		// TODO Auto-generated method stub
		
	}
}
