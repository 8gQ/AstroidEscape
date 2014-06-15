package ca.codybanman.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Projectile {

	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isVisible;


	public Projectile(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, scrollSpeed);
		this.width = width;
		this.height = height;
		isVisible = true;
	}

	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));

		if (position.y < 40){
			isVisible = false;
		}
	}

	public void reset(float newX) {
		position.x = newX;
		isVisible = true;
		height = 149;
	}

	public void hit() {
		isVisible = false;
	}

	public void stop() {
		velocity.y = 0;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
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

}