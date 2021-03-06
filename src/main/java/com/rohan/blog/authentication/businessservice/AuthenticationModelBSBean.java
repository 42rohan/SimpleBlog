package com.rohan.blog.authentication.businessservice;

import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authenticationModelBS")
public class AuthenticationModelBSBean implements AuthenticationModelBS {


	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security

	public boolean login(String username, String password) {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(
						authenticate);				
				return true;
			}
		} catch (AuthenticationException e) {			
		}
		return false;
	}

	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		//currentUser.unauthenticate();
	}

	
}
