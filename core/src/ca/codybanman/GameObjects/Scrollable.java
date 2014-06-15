package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isVisible;
	protected boolean isAlive;

	public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, scrollSpeed);
		this.width = width;
		this.height = height;
		isVisible = true;
		isAlive = true;
	}

	public Scrollable(float x, float y, int width, int height,
			float scrollSpeedX, float scrollSpeedY) {
		position = new Vector2(x, y);
		velocity = new Vector2(scrollSpeedX, scrollSpeedY);
		this.width = width;
		this.height = height;
		isVisible = true;
		isAlive = true;
	}

	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));

		if (position.x + width < 0 || position.x > 136 || position.y > 204
				|| !isAlive) {
			isVisible = false;
		}
	}

	public void stop() {
		velocity.x = 0;
		velocity.y = 0;
	}

	public void hit() {
		isAlive = false;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public boolean isAlive() {
		return isAlive;
	}

}