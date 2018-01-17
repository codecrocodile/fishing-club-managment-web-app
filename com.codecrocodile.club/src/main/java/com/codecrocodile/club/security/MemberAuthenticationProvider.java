package com.codecrocodile.club.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component(value="MemberAuthenticationProvider")
public class MemberAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private MemberDetailsService MemberDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		///TODO do authentication
		
		System.out.println("-=-=-=-=-=-=-=");
		System.out.println("auth provider");
		System.out.println("-=-=-=-=-=-=-=");
		
		if (authentication.getCredentials() != null) {

		}
		
        if (authentication.getCredentials() != null 
        		&& authentication.getName().equals("admin") && authentication.getCredentials().toString().equals("password")) {
			String name = authentication.getName();
			String password = authentication.getCredentials().toString();
			System.out.println("un and pass " + name + " " + password );
			
			MemberDetails userDetails = this.MemberDetailsService.loadUserByUsername(name);
			
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, "ppp", grantedAuths);
            
            return auth;
        } else {
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials");
        }
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
