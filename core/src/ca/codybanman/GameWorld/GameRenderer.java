package ca.codybanman.GameWorld;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.GameObjects.Asteroid;
import ca.codybanman.GameObjects.AsteroidHandler;
import ca.codybanman.GameObjects.Projectile;
import ca.codybanman.GameObjects.ProjectileHandler;
import ca.codybanman.GameObjects.Ship;
import ca.codybanman.ui.SimpleButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private boolean flashed;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	NumberFormat nf = NumberFormat.getInstance();
	DecimalFormat format = (DecimalFormat) nf;

	// Game Objects
	private Ship ship;
	private AsteroidHandler asteroidHandler;
	private ArrayList<Asteroid> asteroids;
	private ProjectileHandler projectileHandler;
	private ArrayList<Projectile> projectiles;

	// Game Assets
	private TextureRegion bg;
	private Animation shipAnimation;
	private TextureRegion ship1;
	private TextureRegion asteroidSml, asteroidMed, asteroidLrg;

	// Buttons
	private List<SimpleButton> menuButtons;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		format.applyPattern("###,##0.00");

		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		menuButtons = myWorld.getButtonHandler().getMenuButtons();

		flashed = false;

		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		ship = myWorld.getShip();
		asteroidHandler = myWorld.getAsteroidHandler();
		projectileHandler = myWorld.getProjectileHanlder();
		asteroids = asteroidHandler.getAsteroids();
		projectiles = projectileHandler.getProjectiles();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		shipAnimation = AssetLoader.shipAnimation;
		ship1 = AssetLoader.ship1;
		asteroidSml = AssetLoader.asteroidSml;
		asteroidMed = AssetLoader.asteroidMed;
		asteroidLrg = AssetLoader.asteroidLrg;
	}

	private void drawAsteroids() {
		if (!asteroids.isEmpty()) {
			for (int i = 0; i < asteroids.size(); i++) {

				switch (asteroids.get(i).getCost()) {
				case 1:
					batcher.draw(asteroidSml, asteroids.get(i).getX(),
							asteroids.get(i).getY(), asteroids.get(i)
									.getWidth(), asteroids.get(i).getHeight());
					break;
				case 2:
					batcher.draw(asteroidMed, asteroids.get(i).getX(),
							asteroids.get(i).getY(), asteroids.get(i)
									.getWidth(), asteroids.get(i).getHeight());
					break;
				case 4:
					batcher.draw(asteroidLrg, asteroids.get(i).getX(),
							asteroids.get(i).getY(), asteroids.get(i)
									.getWidth(), asteroids.get(i).getHeight());
					break;
				}
			}
		}
	}

	private void drawProjectiles() {
		shapeRenderer.setColor(238 / 255.0f, 0 / 255.0f, 138 / 255.0f, 1);
		if (!projectiles.isEmpty()) {
			for (int i = 0; i < projectiles.size(); i++) {
				shapeRenderer.rect(projectiles.get(i).getX(), projectiles
						.get(i).getY(), projectiles.get(i).getWidth(),
						projectiles.get(i).getHeight());
			}
		}
	}

	private void drawMenuUI() {
		batcher.draw(AssetLoader.title, 136 / 2 - 40, midPointY - 50,
				AssetLoader.title.getRegionWidth() / 1.2f,
				AssetLoader.title.getRegionHeight() / 1.2f);

			menuButtons.get(0).draw(batcher);
			menuButtons.get(2).draw(batcher);

	}

	private void drawShip(float runTime) {
		if (ship.isAlive()) {
			batcher.draw(shipAnimation.getKeyFrame(runTime), ship.getX(),
					ship.getY(), ship.getWidth(), ship.getHeight());
		} else {
			batcher.draw(ship1, ship.getX(), ship.getY(), ship.getWidth(),
					ship.getHeight());
		}
	}

	private void drawGameOver() {
		format.applyPattern("###,##0.00");
		if (myWorld.isReady()) {
			AssetLoader.font.setScale(.50f, -.50f);
			AssetLoader.font.draw(batcher, "Touch Here", 68 - 47, 175);
		} else if (myWorld.isGameOver() || myWorld.isHighScore()) {
			if (myWorld.isGameOver()) {
				AssetLoader.font.setScale(.50f, -.50f);
				AssetLoader.font.draw(batcher, "Game Over!", 68 - 47,
						midPointY - 35);
				AssetLoader.font.setScale(.25f, -.25f);
				AssetLoader.font.draw(batcher,
						"  Distance: " + format.format(myWorld.getScore()), 22,
						midPointY - 16);
				AssetLoader.font.draw(
						batcher,
						"High Score: "
								+ format.format(AssetLoader.getHighScore()),
						21, midPointY - 10);
			} else {
				AssetLoader.font.setScale(.50f, -.50f);
				AssetLoader.font.draw(batcher, "High Score!", 68 - 47,
						midPointY - 35);
				AssetLoader.font.setScale(.25f, -.25f);
				AssetLoader.font.draw(batcher,
						"     Score: " + format.format(myWorld.getScore()), 22,
						midPointY - 16);
				AssetLoader.font.draw(
						batcher,
						"High Score: "
								+ format.format(AssetLoader.getHighScore()),
						21, midPointY - 10);
			}
			menuButtons.get(1).draw(batcher);
			menuButtons.get(2).draw(batcher);

		}
	}

	private void drawScore(){
		String score = "Distance: " + format.format(myWorld.getScore());
		AssetLoader.font.setScale(.25f, -.25f);
		AssetLoader.font.draw(batcher, score,
				(136 / 2) - (2 * score.length()), 11);
	}

	private void drawFlash() {
		if (!ship.isAlive() && !flashed) {

			shapeRenderer
					.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1f);
			shapeRenderer.rect(0, 0, 136, 204);
			flashed = true;
		}
	}

	public void render(float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Background color
		shapeRenderer.setColor(23 / 255.0f, 23 / 255.0f, 23 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, 204);

		shapeRenderer.end();

		batcher.begin();



		batcher.disableBlending();
		batcher.draw(bg, 0, 0, 136, midPointY * 2);

		batcher.enableBlending();
		drawAsteroids();
		batcher.end();

		shapeRenderer.begin(ShapeType.Filled);
		drawProjectiles();
		shapeRenderer.end();

		batcher.begin();
		if (myWorld.isRunning()) {
			drawShip(runTime);
			drawScore();
		} else if(myWorld.isReady()){
			drawShip(runTime);
			drawScore();
		} else if (myWorld.isMenu()) {
			drawMenuUI();
		} else if(myWorld.isGameOver() || myWorld.isHighScore()) {
			drawGameOver();
			drawShip(runTime);
		}

		drawGameOver();

		batcher.end();

		shapeRenderer.begin(ShapeType.Filled);
		drawFlash();
		shapeRenderer.end();

	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public int getGameHeight() {
		return gameHeight;
	}
}