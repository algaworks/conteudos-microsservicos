package com.algaworks.example.auth.post.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	public Long getUserId() {
		SecurityContext context = SecurityContextHolder.getContext();
		if(context == null) {
			return null;
		}

		Authentication authentication = context.getAuthentication();
		if(authentication == null) {
			return null;
		}

		if (authentication.getPrincipal() instanceof Jwt) {
			Jwt jwt = (Jwt) authentication.getPrincipal();
			if(jwt == null) {
				return null;
			}

			String userId = jwt.getClaims().get("user_id").toString();
			if(userId == null) {
				return null;
			}

			return Long.parseLong(userId);
		}
		return null;
	}

}