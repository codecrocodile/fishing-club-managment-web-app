package com.codecrocodile.club.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codecrocodile.club.common.DateParam;
import com.codecrocodile.club.dao.WeatherDao;
import com.codecrocodile.club.model.external.Weather;
import com.codecrocodile.club.model.external.WeatherException;
import com.codecrocodile.club.model.lookup.Venue;
import com.codecrocodile.club.service.CatchRecordService;
import com.codecrocodile.club.service.WeatherService;

@Path("/weather-service")
@Produces(MediaType.APPLICATION_JSON)
@Component("WeatherEndPoint")
public class WeatherEndPoint {
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private WeatherDao weatherDao;
	
	@Autowired
	private CatchRecordService catchRecordService;
	
	@GET
	@Path("/singleDayWeather")
	@Produces(MediaType.APPLICATION_JSON)  
	public Weather getWeather(@QueryParam("venueId") long venueId, @QueryParam("date") DateParam date) {
		Weather weather = this.weatherDao.getWeather(venueId, date.getDate());
		
		if (weather == null) {
			try {
				Venue venue = catchRecordService.getVenue(venueId);
				if (venue == null) {
					throw new WeatherException("Venue not recognised");
				}
				String location = StringUtils.join(new Object[] {Double.toString(venue.getLat()), ",", Double.toString(venue.getLon())}); 
				
				System.out.println(location);
				
				weather = this.weatherService.getWeather(location, date.getDate());
				weather.setVenueId(venueId);
				this.weatherDao.saveWeather(weather);
			} catch (WeatherException e) {
				// TODO return error
				e.printStackTrace();
			}
		} 
		
		return weather;
	}
	
}
