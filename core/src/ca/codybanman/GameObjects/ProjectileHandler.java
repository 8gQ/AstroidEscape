package ca.codybanman.GameObjects;

import java.util.ArrayList;

import ca.codybanman.AEHelpers.AssetLoader;

public class ProjectileHandler {

	private ArrayList<Projectile> projectiles;

	private static final int SCROLL_SPEED = -59;
	private static final float SHOT_FREQUENCY = 0.30f;

	private float timeSinceLastShot;

	private Ship ship;

	public ProjectileHandler(Ship ship) {
		this.ship = ship;
		projectiles = new ArrayList<Projectile>();
		timeSinceLastShot = 0;
	}

	public void update(float delta) {
		if (ship.isAlive()) {
			timeSinceLastShot += delta;
			if (timeSinceLastShot >= SHOT_FREQUENCY) {
				shoot();
			}
			for (int i = 0; i < projectiles.size(); i++) {
				projectiles.get(i).update(delta);
				if (!projectiles.get(i).isVisible()) {
					projectiles.remove(i);
				}
			}
		}

	}

	public void onRestart() {
		projectiles.clear();
		timeSinceLastShot = 0;
	}

	public void stop() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).stop();
		}
	}

	private void shoot() {
		projectiles.add(new Projectile(
				ship.getX() + ship.getWidth() / 2 - 0.5f, ship.getY(), 1, 3,
				SCROLL_SPEED));
		AssetLoader.shoot.play();
		timeSinceLastShot = 0;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

}