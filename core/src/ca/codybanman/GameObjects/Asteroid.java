package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Polygon;

public class Asteroid extends Scrollable {

	protected int cost;
	protected Polygon boundingPolygon;

	public Asteroid(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}

	public Asteroid(float x, float y, int width, int height,
			float scrollSpeedX, float scrollSpeedY) {
		super(x, y, width, height, scrollSpeedX, scrollSpeedY);
	}

	public int getCost() {
		return cost;
	}

	public Polygon getBoundingPolygon() {
		return boundingPolygon;
	}

}