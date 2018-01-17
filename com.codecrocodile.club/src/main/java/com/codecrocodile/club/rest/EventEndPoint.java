package com.codecrocodile.club.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codecrocodile.club.common.ExceptionMapperImpl;
import com.codecrocodile.club.model.Event;
import com.codecrocodile.club.model.EventResponse;
import com.codecrocodile.club.service.EventService;

@Path("/event-service")
@Controller
public class EventEndPoint {
	
	@Autowired
	private EventService eventService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Response getEvents(
			@QueryParam(value = "page") int page, 
			@QueryParam(value = "start") int start, 
			@QueryParam(value = "limit") int limit) {
		
		EventResponse postsResponse = new EventResponse();
		postsResponse.setEvents(eventService.getEvents(start));
		postsResponse.setTotalCount(eventService.getTotalCount());
		
		return Response.ok(postsResponse).build();
	}
	
	@POST
	@Path("/{eventId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Event postEvent(Event event) {
		return this.eventService.saveEvent(event);
	}
	
	@PUT
	@Path("/{eventId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Event putEvent(Event event) {
		return this.eventService.saveEvent(event);
	}
	
	@DELETE
	@Path("/{eventId}")
	@ExceptionHandler(ExceptionMapperImpl.class)
	public void deleteEvent(@PathParam("eventId") int eventId) {
		this.eventService.deleteEvent(eventId);
	}

}
