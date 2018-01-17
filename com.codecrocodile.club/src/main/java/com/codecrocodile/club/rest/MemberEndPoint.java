package com.codecrocodile.club.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codecrocodile.club.common.ExceptionMapperImpl;
import com.codecrocodile.club.model.Member;
import com.codecrocodile.club.service.MemberService;

@Controller
@Path("/member-service")
public class MemberEndPoint {
	
	private static Logger log = Logger.getLogger(MemberEndPoint.class);
	
	@Autowired
	private MemberService memberService;
	
	
	@GET
	@Path("/authenticated")
	@RolesAllowed("ROLE_USER")
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Member authenticatedMember() {
		try {
			Member authenticatedMember = memberService.getAuthenticatedMember();
			
			System.out.println("photo " + authenticatedMember.getPhoto());
			
			return authenticatedMember;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PUT
	@Path("/authenticated")
	@RolesAllowed("ROLE_USER")
	@Transactional
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public void authenticatedMember(Member member) {
		try {
			memberService.saveAuthenticatedMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public List<Member> getMembers() {
		return memberService.getMembers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
	@Transactional
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Member saveMember(Member member)  {
		return memberService.saveMember(member);
	}
	
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed("ROLE_ADMIN")
	@Transactional
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public void saveMember(@PathParam("userId") int userId, Member member)  {
		memberService.saveMember(member);
	}
	
	@DELETE
	@Path("/{userId}")
	@ExceptionHandler(ExceptionMapperImpl.class)
	public void deleteMember(@PathParam("userId") int userId) {
		memberService.deleteMember(userId);
	}
	
}
