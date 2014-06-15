package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Polygon;

public class AsteroidSmall extends Asteroid{

	private float[] boundsArray;

	public AsteroidSmall(float x, float y, int width, int height,
			float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		boundingPolygon = new Polygon();
		updateBoundingPolygon();
		cost = 1;
	}

	public AsteroidSmall(float x, float y, int width, int height,
			float scrollSpeedX, float scrollSpeedY) {
		super(x, y, width, height, scrollSpeedX, scrollSpeedY);
		boundingPolygon = new Polygon();
		updateBoundingPolygon();
		cost = 1;
	}



	@Override
	public void update(float delta) {
		super.update(delta);
		updateBoundingPolygon();
	}

	private void updateBoundingPolygon() {
		boundsArray = new float[]{
				getX() + 0, getY() + 9, 
				getX() + 4, getY() + 13, 
				getX() + 5, getY() + 13, 
				getX() + 7, getY() + 11, 
				getX() + 9, getY() + 11,
				getX() + 13, getY() + 7,
				getX() + 13, getY() + 4,
				getX() + 9, getY() + 0,
				getX() + 8, getY() + 0,
				getX() + 4, getY() + 4,
				getX() + 3, getY() + 4,
				getX() + 2, getY() + 5,
				getX() + 2, getY() + 6,
				getX() + 0, getY() + 8
				};

		boundingPolygon.setVertices(boundsArray);
	}

}