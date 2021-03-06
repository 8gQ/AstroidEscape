package ca.codybanman.AstroidEscape.android;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import ca.codybanman.AEHelpers.IActivityRequestHandler;
import ca.codybanman.AstroidEscape.AEGame;
import ca.codybanman.GameServices.ActionResolver;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.facebook.AppEventsLogger;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

@SuppressLint("HandlerLeak")
public class AndroidLauncher extends AndroidApplication implements
		IActivityRequestHandler, GameHelperListener, ActionResolver {

	private GameHelper gameHelper;
	private UiLifecycleHelper uiHelper;

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_ADS: {
				adView.setVisibility(View.VISIBLE);
				break;
			}
			case HIDE_ADS: {
				adView.setVisibility(View.INVISIBLE);
				break;
			}
			}
		}
	};

	AdView adView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGLSurfaceView20API18 = true;
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		RelativeLayout layout = new RelativeLayout(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		View gameView = initializeForView(new AEGame(this, this), cfg);
		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			gameHelper.enableDebugLog(true);
		}
		gameHelper.setup(this);

		uiHelper = new UiLifecycleHelper(this, null);
		uiHelper.onCreate(savedInstanceState);

		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-7512999237077002/4617342779");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("D4514AFD171EE7ABDD56A1FEA2B2D9AF").build();

		adView.setId(12345); // this is an arbitrary id, allows for relative
								// positioning in createGameView()
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				RelativeLayout.TRUE);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		adView.setLayoutParams(adParams);
		adView.setBackgroundColor(Color.argb(1, 23, 23, 23));

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.BELOW, adView.getId());
		gameView.setLayoutParams(params);

		layout.addView(gameView);
		layout.addView(adView, adParams);

		setContentView(layout);

		adView.loadAd(adRequest);
		adView.resume();

	}

	@Override
	public void onResume() {
		super.onResume();
		AppEventsLogger.activateApp(this, "1441691276089861");
		uiHelper.onResume();
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
		gameHelper.onStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
		gameHelper.onStop();
		uiHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data,
				new FacebookDialog.Callback() {
					@Override
					public void onError(FacebookDialog.PendingCall pendingCall,
							Exception error, Bundle data) {
						Log.e("Activity",
								String.format("Error: %s", error.toString()));
					}

					@Override
					public void onComplete(
							FacebookDialog.PendingCall pendingCall, Bundle data) {
						Log.i("Activity", "Success!");
					}
				});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}

	@Override
	public boolean getSignedInGPGS() {
		return gameHelper.isSignedIn();
	}

	@Override
	public void loginGPGS() {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
					System.out.println("Login G+");
				}
			});
		} catch (final Exception ex) {
			System.out.println("Sign in failure");
		}
	}

	@Override
	public void submitScoreGPGS(int score) {
		Games.Leaderboards.submitScore(gameHelper.getApiClient(),
				"CgkIuZDUg8cCEAIQAQ", score);
	}

	@Override
	public void unlockAchievementGPGS(String achievementId) {
		Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
	}

	@Override
	public void getLeaderboardGPGS() {
		this.startActivityForResult(
				Games.Leaderboards.getLeaderboardIntent(
						gameHelper.getApiClient(), "CgkIuZDUg8cCEAIQAQ"),
				15453525);
	}

	@Override
	public void getAchievementsGPGS() {
		startActivityForResult(
				Games.Achievements.getAchievementsIntent(gameHelper
						.getApiClient()), 15154545);
	}

	@Override
	public void signIn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postToFacebook(double scores) {
		int num = (int) (100 * scores);
		float score = ((float) (num)) / 100;
		if (FacebookDialog.canPresentShareDialog(getApplicationContext(),
				FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
			// Publish the post using the Share Dialog
			FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(
					this)
					
					.setName("I just scored " + score + " on Asteroid Escape!")
					.setCaption("Asteroid Escape - (play.google.com)")
					.setDescription("Can you do better?")
					.setPicture("http://s28.postimg.org/qtkhsp07t/ae_launcher.png")
					.setLink(
							"https://play.google.com/store/apps/details?id=ca.codybanman.AstroidEscape")
					.build();
			uiHelper.trackPendingDialogCall(shareDialog.present());

		} else {
			// Fallback. For example, publish the post using the Feed Dialog
		}
	}
}