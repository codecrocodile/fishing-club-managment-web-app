package com.codecrocodile.club.common;

public interface UserSessionIF {
	
	void setUserId(int userId);
	
	int getUserId();

	void incrementCallCount();
	
	int getCallCount();
	
}
