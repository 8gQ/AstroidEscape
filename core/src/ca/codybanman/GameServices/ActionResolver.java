package ca.codybanman.GameServices;

public interface ActionResolver {

	public boolean getSignedInGPGS();
	public void loginGPGS();
	public void submitScoreGPGS(int score);
	public void unlockAchievementGPGS(String achievementId);
	public void getLeaderboardGPGS();
	public void getAchievementsGPGS();
	public void signIn();
	public void postToFacebook(double score);
}