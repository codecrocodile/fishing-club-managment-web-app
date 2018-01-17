package com.codecrocodile.club.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component(value="MemberDetailsService")
public class MemberDetailsService implements UserDetailsService {

	public MemberDetailsService() {
		super();
	}
	
	@Override
	public MemberDetails loadUserByUsername(String username) {

		System.out.println("========================");
		System.out.println("load details");
		System.out.println("========================");
		// these details are not used by spring, trhey are just for information purposes
		MemberDetails memberDetails = new MemberDetails(username, "pppp", AuthorityUtils.createAuthorityList("ROLE_AUTH", "ROLE_USER"));
		memberDetails.setUserId(1);
		memberDetails.setAccountId(1);
	
		return memberDetails;
	}


}
