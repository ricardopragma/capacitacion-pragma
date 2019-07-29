package co.com.pragma.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		addClaims((DefaultOAuth2AccessToken) accessToken);
		return accessToken;
	}

	private void addClaims(DefaultOAuth2AccessToken accessToken) {
		DefaultOAuth2AccessToken token = accessToken;
		Map<String, Object> additionalInformation = token.getAdditionalInformation();
		if (additionalInformation.isEmpty()) {
			additionalInformation = new LinkedHashMap<String, Object>();
		}
		additionalInformation.put("iat", new Integer((int) (System.currentTimeMillis() / 1000L)));
		token.setAdditionalInformation(additionalInformation);
	}
}
