package com.codecrocodile.club.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is to be used in place of Date for service end point query params or path params. If you look at the 
 * API for @QueryParam or @PathParam then it tells you that string value can only be converted to the type
 * you want if:
 * 
 * <quote>
 * Be a primitive type.
 * Have a constructor that accepts a single String argument. 
 * Have a static method named valueOf or fromString that accepts a single String argument (see, for example, Integer.valueOf(String)). 
 * Have a registered implementation of ParamConverterProvider JAX-RS extension SPI that returns a ParamConverter instance capable of a "from string" conversion for the type.
 * </quote>
 * 
 * This is an easy way round the problem.
 * 
 * @author Chris
 */
public class DateParam {
	
	private String date;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public DateParam(String date) {
		this.date = date;
	}
	
	public Date getDate() {
		try {
			return dateFormat.parse(this.date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
