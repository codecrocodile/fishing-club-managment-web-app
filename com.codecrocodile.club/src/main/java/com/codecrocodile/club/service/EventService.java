package com.codecrocodile.club.service;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.codecrocodile.club.common.UnauthorisedException;
import com.codecrocodile.club.model.Event;
import com.codecrocodile.club.model.EventUserResponse;
import com.codecrocodile.club.security.MemberDetails;
import com.codecrocodile.dao.mybatis.mappers.EventMapper;

@Service("EventService")  
@Repository
public class EventService {
	
	private static final int EVENT_PAGE_SIZE = 15;
	
	@Autowired
	private EventMapper eventMapper;
	
	@RolesAllowed("ROLE_ADMIN")
	public List<Event> getEvents(int skipRows) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return this.eventMapper.getEvents(memberDetails.getAccountId(), skipRows, EventService.EVENT_PAGE_SIZE);
	}

	@RolesAllowed("ROLE_ADMIN")
	public int getTotalCount() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return this.eventMapper.getEventCount(memberDetails.getAccountId());
	}
	
	@RolesAllowed("ROLE_ADMIN")
	public Event saveEvent(Event event) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		// TODO fix and finish
		event.setAccountId(memberDetails.getAccountId());
		event.setUserId(memberDetails.getUserId());
		event.setDate(new Date());
		
		
		boolean eventExists = this.eventMapper.isEventExists(event.getEventId(), event.getAccountId());
		
		if (eventExists) {
			this.eventMapper.updateEvent(event);
		} else {
			this.eventMapper.insertEvent(event);
		}
		
		return event;
	}
	
	@RolesAllowed("ROLE_USER")
	public void deleteEvent(int eventId) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		this.eventMapper.retireEvent(eventId, memberDetails.getAccountId());
	}
	
	@RolesAllowed("ROLE_USER")
	public EventUserResponse insertEventResponse(EventUserResponse eventResponse) {
		return null;
	}

	
}
