package co.com.pragma.config.util;

public class WebClientConfiguration {

	private int accessTokenValidityInSeconds = 5 * 60;
	private int refreshTokenValidityInSecondsForRememberMe = 7 * 24 * 60 * 60;
	private String clientId = "web_app";
	private String secret = "changeit";

	public int getAccessTokenValidityInSeconds() {
		return accessTokenValidityInSeconds;
	}

	public void setAccessTokenValidityInSeconds(int accessTokenValidityInSeconds) {
		this.accessTokenValidityInSeconds = accessTokenValidityInSeconds;
	}

	public int getRefreshTokenValidityInSecondsForRememberMe() {
		return refreshTokenValidityInSecondsForRememberMe;
	}

	public void setRefreshTokenValidityInSecondsForRememberMe(int refreshTokenValidityInSecondsForRememberMe) {
		this.refreshTokenValidityInSecondsForRememberMe = refreshTokenValidityInSecondsForRememberMe;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
