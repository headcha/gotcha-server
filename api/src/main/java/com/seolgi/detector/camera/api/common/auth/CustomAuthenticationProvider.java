package com.seolgi.detector.camera.api.common.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthSecurityService authSecurityService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetails user = authSecurityService.loadUserByUsername(id);

		if (user == null || passwordEncoder.matches(password , user.getPassword()) == false)
			throw new BadCredentialsException("암호가 일치하지 않습니다.");

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
		result.setDetails(user);
		return result;

	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
