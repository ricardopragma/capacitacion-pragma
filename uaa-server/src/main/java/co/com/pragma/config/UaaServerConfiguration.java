package co.com.pragma.config;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import co.com.pragma.config.util.Constants;

@Configuration
@EnableAuthorizationServer
public class UaaServerConfiguration extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private final PasswordEncoder passwordEncoder;
	private final UaaServerProperties uaaProperties;

	public UaaServerConfiguration(PasswordEncoder passwordEncoder, UaaServerProperties uaaProperties) {
		this.passwordEncoder = passwordEncoder;
		this.uaaProperties = uaaProperties;
	}

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		int accessTokenValidity = uaaProperties.getWebClientConfiguration().getAccessTokenValidityInSeconds();
		accessTokenValidity = Math.max(accessTokenValidity, Constants.MIN_ACCESS_TOKEN_VALIDITY_SECS);
		int refreshTokenValidity = uaaProperties.getWebClientConfiguration()
				.getRefreshTokenValidityInSecondsForRememberMe();
		refreshTokenValidity = Math.max(refreshTokenValidity, accessTokenValidity);

		clients.inMemory().withClient(uaaProperties.getWebClientConfiguration().getClientId())
				.secret(passwordEncoder.encode(uaaProperties.getWebClientConfiguration().getSecret())).scopes("openid")
				.autoApprove(true).authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
				.accessTokenValiditySeconds(accessTokenValidity).refreshTokenValiditySeconds(refreshTokenValidity).and()
				.withClient("").secret(passwordEncoder.encode("")).scopes("web-app").authorities("ROLE_ADMIN")
				.autoApprove(true).authorizedGrantTypes("client_credentials").accessTokenValiditySeconds((int) 2)
				.refreshTokenValiditySeconds((int) 2);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancerChain).reuseRefreshTokens(false);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(uaaProperties.getKeyStore().getName()),
				uaaProperties.getKeyStore().getPassword().toCharArray())
						.getKeyPair(uaaProperties.getKeyStore().getAlias());
		converter.setKeyPair(keyPair);
		return converter;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}
