package ca.codybanman.AEHelpers;

import ca.codybanman.GameObjects.Ship;
import ca.codybanman.GameWorld.GameWorld;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class InputHandler implements InputProcessor {

	private GameWorld myWorld;
	private Ship myShip;
	private OrthographicCamera cam;

	private Vector3 position;

	public InputHandler(GameWorld myWorld, OrthographicCamera cam) {
		this.myWorld = myWorld;
		myShip = myWorld.getShip();
		this.cam = cam;
		position = new Vector3();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		position.set(screenX, screenY, 0);
		cam.unproject(position);

		if (myWorld.isMenu()) {
			myWorld.getButtonHandler().getPlayButton()
					.isTouchDown(position.x, position.y);
			myWorld.getButtonHandler().getMenuButtons().get(2)
					.isTouchDown(position.x, position.y);
		} else if (myWorld.isReady()) {
			myWorld.start();
		} else if (myWorld.isGameOver() || myWorld.isHighScore()) {
			myWorld.getButtonHandler().getRetryButton()
					.isTouchDown(position.x, position.y);
			myWorld.getButtonHandler().getHighScoreButton()
					.isTouchDown(position.x, position.y);
			myWorld.getButtonHandler().getFacebookButton()
					.isTouchDown(position.x, position.y);
		}
		myShip.touchDown(position.x);

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		position.set(screenX, screenY, 0);
		cam.unproject(position);

		if (myWorld.isMenu()) {
			if (myWorld.getButtonHandler().getPlayButton()
					.isTouchUp(position.x, position.y)) {
				myWorld.ready();
				return true;
			} else if (myWorld.getButtonHandler().getMenuButtons().get(2)
					.isTouchUp(position.x, position.y)) {
				if (!AssetLoader.actionResolver.getSignedInGPGS()) {
					AssetLoader.actionResolver.signIn();
				} else {
					AssetLoader.actionResolver.getLeaderboardGPGS();
				}
				return true;
			}
		}

		if (myWorld.isGameOver() || myWorld.isHighScore()) {
			if (myWorld.getButtonHandler().getRetryButton()
					.isTouchUp(position.x, position.y)) {
				myWorld.restart();
				return true;
			} else if (myWorld.getButtonHandler().getHighScoreButton()
					.isTouchUp(position.x, position.y)) {
				if (!AssetLoader.actionResolver.getSignedInGPGS()) {
					AssetLoader.actionResolver.signIn();
				} else {
					AssetLoader.actionResolver.getLeaderboardGPGS();
				}
				return true;
			} else if (myWorld.getButtonHandler().getFacebookButton()
					.isTouchUp(position.x, position.y)) {
				// TODO post to facebook
				System.out.println("Post to Facebook!");
				return true;
			}
		}

		myShip.touchUp();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		position.set(screenX, screenY, 0);
		cam.unproject(position);
		myShip.touchDragged(position.x);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}