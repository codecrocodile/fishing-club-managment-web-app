/*
 * @(#)MyService.java			26 Apr 2014
 *
 * Copyright (c) 2012-2014 Groovy Fly.
 * 3 Aillort place, East Mains, East Kilbride, Scotland.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Groovy 
 * Fly. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Groovy Fly.
 */
package com.codecrocodile.club.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.codecrocodile.club.model.Account;
import com.codecrocodile.club.model.AccountSettings;

/**
 * @author Chris Hatton
 */
@Component
@Path("/account-service")
public class AccountEndPoint {

	@GET
	@Path("/getAccount")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount() {

		Account account = new Account();
		account.setId(1001l);
		account.setName("Egalesham Angling Association");
		account.setNoOfMembers(140);
//		account.setStartDate(new Date());
//		account.setEndDate(null);
		
		AccountSettings accountSettings = new AccountSettings();
		accountSettings.setCurrentYear("2014");
		accountSettings.setMaxMembers(150);
		
		Calendar sCal = Calendar.getInstance();
		sCal.set(Calendar.YEAR, 2014);
		sCal.set(Calendar.MONTH, 2);
		sCal.set(Calendar.DAY_OF_MONTH, 20);
		sCal.set(Calendar.HOUR_OF_DAY, 0);
		sCal.set(Calendar.MINUTE, 0);
		sCal.set(Calendar.SECOND, 0);
		sCal.set(Calendar.MILLISECOND, 0);
		accountSettings.setSeasonStart(sCal.getTime());
		sCal.add(Calendar.MONTH, 5);
		accountSettings.setSeasonEnd(sCal.getTime());

//		account.setAccountSettings(accountSettings);
		
		return account;
	}
}
