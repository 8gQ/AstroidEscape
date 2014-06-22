package ca.codybanman.AstroidEscape.desktop;

import ca.codybanman.AEHelpers.IActivityRequestHandler;
import ca.codybanman.AstroidEscape.AEGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher implements IActivityRequestHandler{
	public static void main(String[] args) {
		
		DesktopLauncher application = null;
		if (application == null) {
            application = new DesktopLauncher();
        }
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Astroid Escape";
		cfg.width = 272*2;
		cfg.height = 408*2;
		
		new LwjglApplication(new AEGame(application), cfg);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
	}
}
