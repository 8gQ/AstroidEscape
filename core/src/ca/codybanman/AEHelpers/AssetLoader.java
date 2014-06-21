package ca.codybanman.AEHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static IActivityRequestHandler myRequestHandler;
//	public static ActionResolver actionResolver;

	public static Texture texture;

	public static Animation shipAnimation;
	public static TextureRegion bg, ship1, ship2, asteroidSml, asteroidMed,
			asteroidLrg, title, playButtonUp, playButtonDown, retryButtonUp,
			retryButtonDown, highScoreButtonUp, highScoreButtonDown;

	public static Sound shoot, dead, deadAsteroid;

	public static BitmapFont font;

	public static Preferences prefs;

	public static void load(IActivityRequestHandler handler) {
		
		myRequestHandler = handler;

		prefs = Gdx.app.getPreferences("AsteroidEscape");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}

		texture = new Texture(Gdx.files.internal("texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		title = new TextureRegion(texture, 0, 47, 98, 42);
		title.flip(false, true);

		playButtonUp = new TextureRegion(texture, 0, 89, 27, 11);
		playButtonUp.flip(false, true);

		playButtonDown = new TextureRegion(texture, 27, 89, 27, 11);
		playButtonDown.flip(false, true);

		retryButtonUp = new TextureRegion(texture, 0, 100, 57, 11);
		retryButtonUp.flip(false, true);

		retryButtonDown = new TextureRegion(texture, 0, 111, 57, 11);
		retryButtonDown.flip(false, true);

		highScoreButtonUp = new TextureRegion(texture, 0, 122, 54, 11);
		highScoreButtonUp.flip(false, true);

		highScoreButtonDown = new TextureRegion(texture, 0, 133, 54, 11);
		highScoreButtonDown.flip(false, true);

		bg = new TextureRegion(texture, 119, 0, 136, 240);
		bg.flip(false, true);

		ship1 = new TextureRegion(texture, 0, 0, 25, 24);
		ship1.flip(false, true);

		ship2 = new TextureRegion(texture, 25, 0, 25, 24);
		ship2.flip(false, true);

		TextureRegion[] ships = { ship1, ship2 };
		shipAnimation = new Animation(0.03f, ships);
		shipAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		asteroidSml = new TextureRegion(texture, 0, 24, 14, 14);
		asteroidSml.flip(false, true);

		asteroidMed = new TextureRegion(texture, 50, 0, 23, 25);
		asteroidMed.flip(false, true);

		asteroidLrg = new TextureRegion(texture, 73, 0, 46, 47);
		asteroidLrg.flip(false, true);

		shoot = Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));
		dead = Gdx.audio.newSound(Gdx.files.internal("dead.wav"));
		deadAsteroid = Gdx.audio.newSound(Gdx.files
				.internal("deadAsteroid.wav"));

		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setScale(.25f, -.25f);
		
		System.out.println("Assets Loaded!");

	}

	public static void setHighScore(float val) {
		prefs.putFloat("highScore", val);
		prefs.flush();
	}

	public static float getHighScore() {
		return prefs.getFloat("highScore");
	}

	public static void dispose() {
		texture.dispose();
		shoot.dispose();
		dead.dispose();
		deadAsteroid.dispose();
		font.dispose();
	}

}