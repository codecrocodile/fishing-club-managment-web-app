package com.codecrocodile.club.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codecrocodile.club.common.ExceptionMapperImpl;
import com.codecrocodile.club.model.accounting.Invoice;
import com.codecrocodile.club.service.AccountingService;

@Path("/accounting-service")
@Controller
public class AccountingEndPoint {
	
	@Autowired
	private AccountingService accountingService;
	
	@GET
	@Path("/authenticated")
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public List<Invoice> authenticatedMemberInvoices() {
		return accountingService.authenticatedMemberInvoices();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public List<Invoice> getInvoicesForMember(@QueryParam("userId") int userId) {
		return accountingService.getInvoicesForMember(userId);
	}

}
