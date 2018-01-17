package com.codecrocodile.club.dao;

import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Component;

@Component
public class MembersDao implements MembersDaoIF {

	@RolesAllowed("ROLE_ADMIN")
	public void doSomething() {
		System.out.println("do somthing.....................");
	}
}
