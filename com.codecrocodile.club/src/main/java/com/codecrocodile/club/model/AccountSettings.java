package com.codecrocodile.club.model;

import java.util.Date;

public class AccountSettings {
	
	private String currentYear;
	
	private int maxMembers;
	
	private Date seasonStart;
	
	private Date seasonEnd;
	
	
	public AccountSettings() {
		super();
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public int getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	public Date getSeasonStart() {
		return seasonStart;
	}

	public void setSeasonStart(Date seasonStart) {
		this.seasonStart = seasonStart;
	}

	public Date getSeasonEnd() {
		return seasonEnd;
	}

	public void setSeasonEnd(Date seasonEnd) {
		this.seasonEnd = seasonEnd;
	}

}
