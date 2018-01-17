package com.codecrocodile.club.common;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

@SuppressWarnings("serial")
public class ExceptionMapperImpl extends Throwable implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		Response response = null;
		ExceptionReponse exceptionReponse = new ExceptionReponse();
		if (exception instanceof UnauthorisedException) {
			exceptionReponse.setExceptionMessage(exception.getMessage());
			response = Response.status(Status.UNAUTHORIZED).entity(exceptionReponse).build();
		} else {
			exception.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return response;
	}

}
