package com.codecrocodile.club.rest;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codecrocodile.club.common.ExceptionMapperImpl;
import com.codecrocodile.club.model.catchrecord.CatchLogResponse;
import com.codecrocodile.club.model.catchrecord.CatchRecord;
import com.codecrocodile.club.model.catchrecord.CatchRecordValidator;
import com.codecrocodile.club.service.CatchRecordService;

@Path("/catchrecord-service")
@Controller
public class CatchRecordEndPoint {
	
	@Autowired
	private CatchRecordService catchRecordService;
	
	@Autowired
	private Validator validator;
	
	
	@POST
	@Path("/catch-record-by-date")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_USER")
	public CatchRecord catchRecord(@FormParam("dateParam") String date) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date parsedDate = df.parse(date);
			return this.catchRecordService.getCatchRecord(parsedDate);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@POST
	@Path("/saveCatchRecord")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_USER")
	public CatchRecord saveCatchRecord(CatchRecord catchRecord) {
		catchRecordService.saveCatchRecord(catchRecord);

		BeanPropertyBindingResult result = new BeanPropertyBindingResult(catchRecord, "myCatchRecord");
		ValidationUtils.invokeValidator(new CatchRecordValidator(), catchRecord, result);
		List<ObjectError> errors = result.getAllErrors();
		System.out.println("No of validation errors: " + errors.size());
		
		return catchRecord;
	}
	
	@GET
	@Path("/catchlog")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_USER")
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Response getCatchLogs(
			@QueryParam(value = "page") int page, 
			@QueryParam(value = "start") int start, 
			@QueryParam(value = "limit") int limit) {
		
		CatchLogResponse catchLogResponse = new CatchLogResponse();
		catchLogResponse.setCatchLogs(catchRecordService.getCatchLogs(start));
		catchLogResponse.setTotalCount(catchRecordService.getCatchLogTotalCount());
		
		return Response.ok(catchLogResponse).build();
	}
}
