package com.codecrocodile.club.common;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.validation.ValidationError;
import org.glassfish.jersey.server.validation.internal.ValidationHelper;

public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {


	@Override
	public Response toResponse(ValidationException exception) {
		Response response = null;
		ExceptionReponse exceptionReponse = new ExceptionReponse();
		
		if (exception instanceof ConstraintViolationException) {
			ConstraintViolationException e  = (ConstraintViolationException) exception;
			List<ValidationError> validationErrors = ValidationHelper.constraintViolationToValidationErrors(e);
			exceptionReponse.setExceptionMessage("Validation Error");
			exceptionReponse.setValidationErrors(validationErrors);
			response = Response.status(Status.BAD_REQUEST).entity(exceptionReponse).build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}
}