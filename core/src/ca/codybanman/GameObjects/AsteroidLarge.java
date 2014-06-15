package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Polygon;

public class AsteroidLarge extends Asteroid {

	private float[] boundsArray;

	public AsteroidLarge(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		boundingPolygon = new Polygon();
		updateBoundingPolygon();
		cost = 4;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		updateBoundingPolygon();
	}

	private void updateBoundingPolygon() {
		boundsArray = new float[]{ 
				getX() + 0, getY() + 24, 
				getX() + 3, getY() + 29,
				getX() + 4, getY() + 32, 
				getX() + 4, getY() + 43, 
				getX() + 9, getY() + 46, 
				getX() + 15, getY() + 46,
				getX() + 19, getY() + 45, 
				getX() + 25, getY() + 41, 
				getX() + 33, getY() + 39, 
				getX() + 38, getY() + 35, 
				getX() + 42, getY() + 26, 
				getX() + 42, getY() + 17, 
				getX() + 45, getY() + 14,
				getX() + 45, getY() + 8,
				getX() + 44, getY() + 6,
				getX() + 37, getY() + 1,
				getX() + 27, getY() + 1,
				getX() + 23, getY() + 5,
				getX() + 15, getY() + 6,
				getX() + 8, getY() + 7,
				getX() + 3, getY() + 10,
				getX() + 0, getY() + 5
				};
		boundingPolygon.setVertices(boundsArray);
	}

}