package com.codecrocodile.club.common;

public class UserSession implements UserSessionIF {
	
	private int userId;
	
	private int callCount;

	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int getUserId() {
		return this.userId;
	}

	@Override
	public void incrementCallCount() {
		this.callCount++;
	}

	@Override
	public int getCallCount() {
		return this.callCount;
	}

}
