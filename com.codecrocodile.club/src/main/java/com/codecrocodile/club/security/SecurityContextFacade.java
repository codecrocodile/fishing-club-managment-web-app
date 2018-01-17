package com.codecrocodile.club.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value="SecurityContextFacade")
public class SecurityContextFacade {

	public MemberDetails getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return memberDetails;
	}
	
	public int getCurrentUserId() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return memberDetails.getUserId();
	}
	
	public int getCurrentAccountId() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return memberDetails.getAccountId();
	}
	
}
