package ca.codybanman.GameWorld;

import java.util.ArrayList;

import ca.codybanman.AEHelpers.AssetLoader;
import ca.codybanman.GameObjects.Asteroid;
import ca.codybanman.GameObjects.AsteroidHandler;
import ca.codybanman.GameObjects.ButtonHandler;
import ca.codybanman.GameObjects.Projectile;
import ca.codybanman.GameObjects.ProjectileHandler;
import ca.codybanman.GameObjects.Ship;

import com.badlogic.gdx.math.Intersector;

public class GameWorld {

	private Ship ship;
	private AsteroidHandler asteroidHandler;
	private ProjectileHandler projectileHandler;
	private ButtonHandler buttonHandler;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<Projectile> projectiles;

	private float score;
	private int midPointX;
	private int midPointY;

	private GameState currentState;

	public enum GameState {
		READY, RUNNING, GAMEOVER, HIGHSCORE, MENU
	}

	public GameWorld(int midPointX, int midPointY) {
		this.midPointX = midPointX;
		this.midPointY = midPointY;
		currentState = GameState.MENU;
		
		// Initialize GameObjects here
		ship = new Ship(midPointX - 12, 150, 25, 24);
		asteroidHandler = new AsteroidHandler();
		projectileHandler = new ProjectileHandler(ship);
		asteroids = asteroidHandler.getAsteroids();
		projectiles = projectileHandler.getProjectiles();
		buttonHandler = new ButtonHandler(this);

		score = 0;

	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			AssetLoader.myRequestHandler.showAds(false);
			break;
		case MENU:
			updateMenu(delta);
			AssetLoader.myRequestHandler.showAds(true);
			break;
		case RUNNING:
			updateRunning(delta);
			AssetLoader.myRequestHandler.showAds(false);
			break;
		case GAMEOVER:
			AssetLoader.myRequestHandler.showAds(true);
			break;
		case HIGHSCORE:
			AssetLoader.myRequestHandler.showAds(true);
			break;
		default:
			break;
		}

	}

	private void updateMenu(float delta) {
		asteroidHandler.update(delta);
	}

	private void updateReady(float delta) {
		asteroidHandler.onRestart();
	}

	private void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		addScore(0.02f);
		ship.update(delta);
		asteroidHandler.update(delta);
		projectileHandler.update(delta);
		checkCollisions();

	}

	public void checkCollisions() {
		if (currentState != GameState.MENU) {
			for (int i = 0; i < asteroids.size(); i++) {
				for (int j = 0; j < projectiles.size(); j++) {
					if (asteroids
							.get(i)
							.getBoundingPolygon()
							.contains(projectiles.get(j).getX(),
									projectiles.get(j).getY())) {
						asteroids.get(i).hit();
						projectiles.get(j).hit();
						AssetLoader.deadAsteroid.play();
					}
					if (asteroids.get(i).getY() >= ship.getHeight()
							- asteroids.get(i).getHeight()) {
						if (Intersector.overlapConvexPolygons(asteroids.get(i)
								.getBoundingPolygon(), ship
								.getBoundingPolygon())) {
							System.out.println("Hit!!!!");
							ship.hit();
							asteroidHandler.stop();
							projectileHandler.stop();
							AssetLoader.dead.play();
							currentState = GameState.GAMEOVER;

							if (score > AssetLoader.getHighScore()) {
								AssetLoader.setHighScore(score);
								currentState = GameState.HIGHSCORE;
							}
							break;
						}
					}
				}

				if (currentState == GameState.GAMEOVER
						|| currentState == GameState.HIGHSCORE) {
					break;
				}
			}
		}
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void restart() {
		currentState = GameState.READY;
		score = 0;
		ship.onRestart(midPointX - 12, 150, 25, 24);
		asteroidHandler.onRestart();
		projectileHandler.onRestart();
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public double getScore() {
		return score;
	}

	public void addScore(float increment) {
		score += increment;
	}

	public Ship getShip() {
		return ship;
	}

	public AsteroidHandler getAsteroidHandler() {
		return asteroidHandler;
	}

	public ProjectileHandler getProjectileHanlder() {
		return projectileHandler;
	}

	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}

	public int getMidPointX() {
		return midPointX;
	}

	public int getMidPointY() {
		return midPointY;
	}

	public void ready() {
		currentState = GameState.READY;

	}
}