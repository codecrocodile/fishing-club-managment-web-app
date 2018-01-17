package com.codecrocodile.club.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberDetails extends User {
	
	private static final long serialVersionUID = 5539444601291787247L;
	
	private int userId;
	
	private int accountId;

	
    public MemberDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
