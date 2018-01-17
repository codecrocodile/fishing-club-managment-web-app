package com.codecrocodile.club.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.codecrocodile.club.model.external.Weather;
import com.codecrocodile.club.model.external.WeatherException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Weather service is provided by www.worldweatheronline.com, and it is a free
 * weather service that allows 5 queries per second, and 12,000 queries per day.
 * 
 * Restrictions on the queries for past weather conditions are restricted to the 
 * last 60 days or 15 days in the future.
 * 
 * Login details are: Username: chris@codecrocodile.com Password: doggydad
 * 
 */
@Service("WeatherService")
@Repository
public class WeatherService {

	public static String WS_API_KEY = "2806ba74a32019fa018a14ffc6998";

	public static String WS_URL = "http://api.worldweatheronline.com";
	
	public static String WS_FUTURE_WEATHER_PATH = "/free/v2/weather.ashx";
	
	public static String WS_PAST_WEATHER_PATH = "/free/v2/past-weather.ashx";
	
	public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Gets the weather for a given location and date. Can only get weather from 60 days
	 * in the past to 15 days in the future.
	 * 
	 * @param location
	 * 		This can be US Zipcode, UK Postcode, Canada Postalcode, IP address, 
	 * 		Latitude/Longitude (decimal degree) or city name
	 * @param date
	 * 		Date from 60 days in the past to 4 days in the future
	 * @return
	 * 		Weather for the 24 average for the given date
	 */
	public Weather getWeather(String location, Date date) throws WeatherException {
		Weather weather = null;
		
		Calendar nowCal = Calendar.getInstance();
		nowCal.set(Calendar.HOUR_OF_DAY, 0);
		nowCal.set(Calendar.MINUTE, 0);
		nowCal.set(Calendar.SECOND, 0);
		nowCal.set(Calendar.MILLISECOND, 0);

		Calendar requestCal = Calendar.getInstance();
		requestCal.setTime(date);
		requestCal.set(Calendar.HOUR_OF_DAY, 0);
		requestCal.set(Calendar.MINUTE, 0);
		requestCal.set(Calendar.SECOND, 0);
		requestCal.set(Calendar.MILLISECOND, 0);
		
		DateTime nowDt = new DateTime(nowCal);
		DateTime requestDt = new DateTime(requestCal);
		
		if (requestCal.before(nowCal)) {
			int days = Days.daysBetween(nowDt, requestDt).getDays();
			if (days < -60) {
				throw new WeatherException("Date too far in past. 60 days in past allowed");
			}
			weather = this.getPastWeather(location, date);
		} else {
			int days = Days.daysBetween(nowDt, requestDt).getDays();
			if (days > 4) {
				throw new WeatherException("Date too far in future. 4 days in future allowed");
			}
			weather = this.getTodayFutureWeather(location, date);
		}
		
		return weather;
	}
	
	private Weather getTodayFutureWeather(String location, Date date) throws WeatherException{
		Weather weather = null;
		Client client = ClientBuilder.newClient();
		
		String responseString = client.target(WS_URL)
	            .path(WS_FUTURE_WEATHER_PATH)
	            .queryParam("q", location)
	            .queryParam("date", this.simpleDateFormat.format(date)) // today or a date in the future
	            .queryParam("tp", "24") // time period (24 hour average)
	            .queryParam("cc", "no") // current conditions (no, not required)
	            .queryParam("format", "json")
	            .queryParam("key", WS_API_KEY)
	            .request(MediaType.APPLICATION_JSON)
	            .get(String.class);
		
		weather = this.convertResponse(responseString).get(0);
		
		return weather;
	}
	
	private Weather getPastWeather(String location, Date date) throws WeatherException {
		Weather weather = null;
		Client client = ClientBuilder.newClient();
		
		String responseString = client.target(WS_URL)
	            .path(WS_PAST_WEATHER_PATH)
	            .queryParam("q", location)
	            .queryParam("date", this.simpleDateFormat.format(date)) // today or a date in the future
	            .queryParam("tp", "24") // time period (24 hour average)
	            .queryParam("format", "json")
	            .queryParam("key", WS_API_KEY)
	            .request(MediaType.APPLICATION_JSON)
	            .get(String.class);
		
		weather = this.convertResponse(responseString).get(0);
		
		return weather;
	}

	/**
	 * Given a response string from the weather service containing JSON data this will convert that response
	 * into a list of Weather objects.
	 * 
	 * @param responseString - JSON response from weather service.
	 * @return List<Weather> - a list of Weather objects.
	 */
	List<Weather> convertResponse(String responseString) throws WeatherException {
		List<Weather> weather = new ArrayList<>();
		// Convert to Java object using Jackson
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode responseTree = objectMapper.readTree(responseString);
			
			JsonNode errorNode = responseTree.findValue("error");
			if (errorNode != null) {
				JsonNode jsonNode = errorNode.findValue("msg");
				throw new WeatherException(jsonNode.asText());
			}
			
			JsonNode weatherNode = responseTree.findValue("weather");
			
			String string = weatherNode.toString();
			
			JsonNode arrNode = new ObjectMapper().readTree(string);
			if (arrNode.isArray()) {
			    for (JsonNode objNode : arrNode) {
			        weather.add(objectMapper.readValue(objNode.toString(), Weather.class));
			    }
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return weather;
	}
	
}
