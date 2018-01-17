package com.codecrocodile.club.model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
	
	private Long Id;
	
	private String name;
	
//	private Date startDate;
	
//	private Date endDate;
	
	private int noOfMembers;
	
//	private AccountSettings accountSettings;
	
	
	public Account() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}

	public int getNoOfMembers() {
		return noOfMembers;
	}

	public void setNoOfMembers(int noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
//
//	public AccountSettings getAccountSettings() {
//		return accountSettings;
//	}

//	public void setAccountSettings(AccountSettings accountSettings) {
//		this.accountSettings = accountSettings;
//	}
//	
}
