package com.codecrocodile.club.common;

import javax.ws.rs.ext.Provider;

@Provider
@SuppressWarnings("serial")
public class UnauthorisedException extends RuntimeException {
	
	public UnauthorisedException(String message) {
		super(message);
	}

}
