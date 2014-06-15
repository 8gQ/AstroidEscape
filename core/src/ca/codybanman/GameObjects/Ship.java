package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Ship {

	private float[] boundsArray;
	private Polygon boundingPolygon;

	private Vector2 position;

	private int width;
	private int height;

	private float newX;
	private float oldX;

	private boolean clicked;
	private boolean alive;

	public Ship(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		oldX = x;
		newX = x;
		clicked = false;
		alive = true;
		boundingPolygon = new Polygon();
		updateBoundingPolygon();
	}

	public void update(float delta) {
		if (alive) {
			if (clicked) {
				position.x -= oldX - newX;
				oldX = newX;
			}

			if (position.x < 0) {
				position.x = 0;
			} else if (position.x > 137 - width) {
				position.x = 137 - width;
			}
			updateBoundingPolygon();
		}
	}

	public void onRestart(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		clicked = false;
		alive = true;
		updateBoundingPolygon();
	}

	public void touchDown(float x) {
		oldX = x;
		newX = x;
		clicked = true;
	}

	public void touchDragged(float x) {
		newX = x;
	}

	public void touchUp() {
		clicked = false;
	}

	private void updateBoundingPolygon() {
		boundsArray = new float[] { getX() + 11, getY() + 0, getX() + 0,
				getY() + 16, getX() + 0, getY() + 17, getX() + 3, getY() + 20,
				getX() + 21, getY() + 20, getX() + 24, getY() + 17,
				getX() + 24, getY() + 16, getX() + 13, getY() + 0 };
		boundingPolygon.setVertices(boundsArray);
	}

	public void hit() {
		alive = false;
	}

	public Polygon getBoundingPolygon() {
		return boundingPolygon;
	}

	public boolean isAlive() {
		return alive;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}