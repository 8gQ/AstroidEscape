package ca.codybanman.AEHelpers;

import ca.codybanman.GameServices.ActionResolver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static IActivityRequestHandler myRequestHandler;
	public static ActionResolver actionResolver;

	public static AssetManager assets;
	public static TextureAtlas spriteSheet;

	public static Animation shipAnimation;

	public static Sprite bg, ship1, ship2, asteroidSml, asteroidMed,
			asteroidLrg, title, playButtonUp, playButtonDown, retryButtonUp,
			retryButtonDown, highScoreButtonUp, highScoreButtonDown,
			facebookUp, facebookDown;

	public static Sound shoot, dead, deadAsteroid;

	public static BitmapFont font;

	public static Preferences prefs;

	public static void load(IActivityRequestHandler handler,
			ActionResolver resolver) {

		myRequestHandler = handler;
		actionResolver = resolver;

		prefs = Gdx.app.getPreferences("AsteroidEscape");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		
		assets = new AssetManager();
		assets.load("spritesheet.pack", TextureAtlas.class);
		assets.finishLoading();

		spriteSheet = assets.get("spritesheet.pack");

		title = spriteSheet.createSprite("Title");
		title.flip(false, true);

		playButtonUp = spriteSheet.createSprite("Play_Up");
		playButtonUp.flip(false, true);

		playButtonDown = spriteSheet.createSprite("Play_Down");
		playButtonDown.flip(false, true);

		retryButtonUp = spriteSheet.createSprite("PlayAgain_Up");
		retryButtonUp.flip(false, true);

		retryButtonDown = spriteSheet.createSprite("PlayAgain_Down");
		retryButtonDown.flip(false, true);

		highScoreButtonUp = spriteSheet.createSprite("Highscore_Up");
		highScoreButtonUp.flip(false, true);

		highScoreButtonDown = spriteSheet.createSprite("Highscore_Down");
		highScoreButtonDown.flip(false, true);

		bg = spriteSheet.createSprite("Background");
		bg.flip(false, true);

		ship1 = spriteSheet.createSprite("Ship2");
		ship1.flip(false, true);

		ship2 = spriteSheet.createSprite("Ship3");
		ship2.flip(false, true);

		TextureRegion[] ships = { ship1, ship2 };
		shipAnimation = new Animation(0.03f, ships);
		shipAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		asteroidSml = spriteSheet.createSprite("Asteroid_sml");
		asteroidSml.flip(false, true);

		asteroidMed = spriteSheet.createSprite("Asteroid_med");
		asteroidMed.flip(false, true);

		asteroidLrg = spriteSheet.createSprite("Asteroid_lrg");
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
		if (actionResolver.getSignedInGPGS()) {
			actionResolver
					.submitScoreGPGS((int) (prefs.getFloat("highScore")) * 100);
		}
	}

	public static float getHighScore() {
		return prefs.getFloat("highScore");
	}

	public static void dispose() {
		spriteSheet.dispose();
		shoot.dispose();
		dead.dispose();
		deadAsteroid.dispose();
		font.dispose();
	}

}