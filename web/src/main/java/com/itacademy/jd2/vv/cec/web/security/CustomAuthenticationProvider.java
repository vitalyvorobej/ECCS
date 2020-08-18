package com.itacademy.jd2.vv.cec.web.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vv.cec.dao.api.model.IUserAccount;
import com.itacademy.jd2.vv.cec.service.IUserAccountService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = authentication.getPrincipal() + ""; // юзернейм
		final String password = authentication.getCredentials() + ""; // пассворд

		final IUserAccount user = userAccountService.getByMail(username);

		if (user == null) {
			throw new BadCredentialsException("1000");
		}
		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = user.getId();

		final List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new ExtendedUsernamePasswordAuthenticationToken(userId, username, password, roles);

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
