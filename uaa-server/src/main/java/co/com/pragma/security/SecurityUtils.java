package co.com.pragma.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

	private SecurityUtils() {
	}

	public static Optional<String> getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				return springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				return (String) authentication.getPrincipal();
			}
			return null;
		});
	}

	public static boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable(securityContext.getAuthentication())
				.map(authentication -> authentication.getAuthorities().stream().noneMatch(
						grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)))
				.orElse(false);
	}

	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional
				.ofNullable(securityContext.getAuthentication()).map(authentication -> authentication.getAuthorities()
						.stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
				.orElse(false);
	}
}
