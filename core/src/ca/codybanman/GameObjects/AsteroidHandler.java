package ca.codybanman.GameObjects;

import java.util.ArrayList;
import java.util.Random;

public class AsteroidHandler {

	private Random r;

	private ArrayList<Asteroid> asteroids;

	private static final int SCROLL_SPEED = 100;
	private static final int MAX_COST = 20;

	private int totalCost;

	public AsteroidHandler() {
		r = new Random();
		totalCost = 0;
		asteroids = new ArrayList<Asteroid>();

		generateAsteroids();

	}

	public void update(float delta) {

		updateAsteroids(delta);

	}

	private void updateAsteroids(float delta) {

		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update(delta);
			if (!asteroids.get(i).isVisible()) {
				Asteroid asteroid = asteroids.get(i);
				switch (asteroid.getCost()) {
				case 1:
					totalCost -= asteroid.getCost();
					asteroids.remove(i);
					break;
				case 2:
					asteroids.add(new AsteroidSmall(asteroid.getX(), asteroid
							.getY(), 14, 14, r.nextInt((int) (asteroid
							.getVelocity().y + 1)) / 3, r
							.nextInt(SCROLL_SPEED - 10) + 10));
					asteroids.add(new AsteroidSmall(asteroid.getX(), asteroid
							.getY(), 14, 14, -r.nextInt((int) (asteroid
							.getVelocity().y + 1)) / 3, r
							.nextInt(SCROLL_SPEED - 10) + 10));
					asteroids.remove(i);
					break;
				case 4:
					asteroids.add(new AsteroidMedium(asteroid.getX(), asteroid
							.getY(), 23, 25, r.nextInt((int) (asteroid
							.getVelocity().y + 1)) / 3, r
							.nextInt(SCROLL_SPEED - 10) + 10));
					asteroids.add(new AsteroidMedium(asteroid.getX(), asteroid
							.getY(), 23, 25, -r.nextInt((int) (asteroid
							.getVelocity().y + 1)) / 3, r
							.nextInt(SCROLL_SPEED - 10) + 10));
					asteroids.remove(i);
					break;
				}
			}
		}

		generateAsteroids();
	}

	private void generateAsteroids() {
		if (totalCost <= MAX_COST) {
			float lastY = 0;
			while (totalCost < MAX_COST) {
				int i = r.nextInt(10);
				switch (i) {
				case 0:
				case 1:
					if (lastY > -14)
						lastY = -14;
					asteroids.add(new AsteroidSmall(r.nextInt(136*100 - 14*100)/100, lastY
							- r.nextInt(100), 14, 14, SCROLL_SPEED
							- r.nextInt(SCROLL_SPEED / 2)));
					totalCost += 1;
					lastY = asteroids.get(asteroids.size() - 1).getY() - 14;
					break;
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					if (lastY > -25)
						lastY = -25;
					asteroids.add(new AsteroidMedium(r.nextInt(136*100 - 23*100)/100, lastY
							- r.nextInt(100), 23, 25, SCROLL_SPEED
							- r.nextInt(SCROLL_SPEED / 2)));
					totalCost += 2;
					lastY = asteroids.get(asteroids.size() - 1).getY() - 25;
					break;
				case 7:
				case 8:
				case 9:
					if (lastY > -47)
						lastY = -47;
					asteroids.add(new AsteroidLarge(r.nextInt(136*100 - 46*100)/100, lastY
							- r.nextInt(100), 46, 47, SCROLL_SPEED
							- r.nextInt(SCROLL_SPEED / 2)));
					totalCost += 4;
					lastY = asteroids.get(asteroids.size() - 1).getY() - 46;
					break;
				}
			}
		}
	}

	public void onRestart() {
		asteroids.clear();
		totalCost = 0;
		generateAsteroids();
	}

	public void stop() {
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).stop();
		}
	}

	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

}