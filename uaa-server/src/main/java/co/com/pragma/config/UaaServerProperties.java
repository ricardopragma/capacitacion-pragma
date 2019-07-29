package co.com.pragma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import co.com.pragma.config.util.KeyStore;
import co.com.pragma.config.util.WebClientConfiguration;

@Component
@ConfigurationProperties(prefix = "uaa", ignoreUnknownFields = false)
public class UaaServerProperties {

	private KeyStore keyStore = new KeyStore();
	private WebClientConfiguration webClientConfiguration = new WebClientConfiguration();

	public KeyStore getKeyStore() {
		return this.keyStore;
	}

	public WebClientConfiguration getWebClientConfiguration() {
		return webClientConfiguration;
	}
}
