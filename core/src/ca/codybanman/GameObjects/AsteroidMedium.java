package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Polygon;

public class AsteroidMedium extends Asteroid{

	private float[] boundsArray;

	public AsteroidMedium(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		boundingPolygon = new Polygon();
		updateBoundingPolygon();
		cost = 2;
	}

	public AsteroidMedium(float x, float y, int width, int height,
			float scrollSpeedX, float scrollSpeedY) {
		super(x, y, width, height, scrollSpeedX, scrollSpeedY);
		boundingPolygon = new Polygon();
		updateBoundingPolygon();
		cost = 2;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		updateBoundingPolygon();
	}

	private void updateBoundingPolygon() {
		boundsArray = new float[]{ 
				getX() + 0, getY() + 17, 
				getX() + 5, getY() + 23, 
				getX() + 8, getY() + 24, 
				getX() + 12, getY() + 24, 
				getX() + 12, getY() + 23, 
				getX() + 15, getY() + 23, 
				getX() + 17, getY() + 22,
				getX() + 20, getY() + 17,
				getX() + 20, getY() + 12,
				getX() + 21, getY() + 12, 
				getX() + 22, getY() + 10,
				getX() + 22, getY() + 8,
				getX() + 17, getY() + 1,
				getX() + 13, getY() + 0,
				getX() + 11, getY() + 0,
				getX() + 8, getY() + 1,
				getX() + 2, getY() + 11,
				getX() + 0, getY() + 14
				};
		boundingPolygon.setVertices(boundsArray);
	}

}