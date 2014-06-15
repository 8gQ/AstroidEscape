package ca.codybanman.AstroidEscape.desktop;

import ca.codybanman.AstroidEscape.AEGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Astroid Escape";
		cfg.width = 272*2;
		cfg.height = 408*2;
		
		new LwjglApplication(new AEGame(), cfg);
	}
}
