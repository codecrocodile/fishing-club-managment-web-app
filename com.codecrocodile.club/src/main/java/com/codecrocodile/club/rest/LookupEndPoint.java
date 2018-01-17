package com.codecrocodile.club.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codecrocodile.club.model.lookup.AllLookups;
import com.codecrocodile.club.model.lookup.Venue;
import com.codecrocodile.club.service.LookupService;

@Path("/lookup-service")
@Component
public class LookupEndPoint {
	
	private static Logger log = Logger.getLogger(LookupService.class);
	
	@Autowired
	private LookupService lookupService;

	@GET
	@Path("/allLookups")
	@Produces(MediaType.APPLICATION_JSON)
	public AllLookups allLookups() {
		AllLookups allLookups = new AllLookups();
		allLookups.setVenues(lookupService.getVenues());
		
		System.out.println(lookupService.getVenues().size());
		
		return allLookups;
	}

	@GET
	@Path("/venues")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venue> venues() {
		log.debug("entering venue");
		
		System.out.println("entering venue " + lookupService.getVenues().size());
		log.info("entering venue " + lookupService.getVenues().size());
		
		return lookupService.getVenues();
	}

}
