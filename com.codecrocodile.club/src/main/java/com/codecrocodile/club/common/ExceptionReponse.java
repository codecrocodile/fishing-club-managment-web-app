package com.codecrocodile.club.common;

import java.util.List;

import org.glassfish.jersey.server.validation.ValidationError;


public class ExceptionReponse {
	
	private String success = "false";
	
	private String message = "error";
	
	private String exceptionMessage;
	
	private List<ValidationError> validationErrors;
	
	public ExceptionReponse() {
		super();
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
