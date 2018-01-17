package com.codecrocodile.club.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.codecrocodile.club.common.DateParam;
import com.codecrocodile.club.dao.WeatherDao;
import com.codecrocodile.club.model.external.Weather;
import com.codecrocodile.club.model.external.WeatherException;
import com.codecrocodile.club.model.lookup.Venue;
import com.codecrocodile.club.service.CatchRecordService;
import com.codecrocodile.club.service.WeatherService;


@RunWith(MockitoJUnitRunner.class)
@Ignore
public class WeatherEndPointTest {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@InjectMocks
	private WeatherEndPoint weatherEndPoint;
	
	@Mock
	private WeatherService weatherService;
	
	@Mock
	private WeatherDao weatherDao;
	
	@Mock
	private CatchRecordService catchRecordService;
	
	/**
	 * Test existing weather is returned when we have it.
	 */
	@Test
	public void testExistingWeather() {
		// init vars
		long venueId = 1;
		Date nowMidnight = DateTime.now().withTimeAtStartOfDay().toDate();
		DateParam dateParam = new DateParam(dateFormat.format(nowMidnight));
		
		// set-up
		Mockito.when(weatherDao.getWeather(venueId, nowMidnight)).thenReturn(new Weather());
		
		// do
		Weather weather = weatherEndPoint.getWeather(venueId, dateParam);
		
		// test
		Assert.assertThat(weather, notNullValue());
		Mockito.verify(weatherDao, Mockito.times(1)).getWeather(venueId, nowMidnight);
	}
	
	/**
	 * Test when we don't have existing weather.
	 * 
	 * @throws WeatherException 
	 */
	@Test
	public void testNonExistingWeather() throws WeatherException {
		// init vars
		long venueId = 1;
		String location = "-4.5323,50.85626";
		Venue venue = new Venue();
		venue.setLat((float) -4.5323);
		venue.setLon((float) 50.85626);;
		Date nowMidnight = DateTime.now().withTimeAtStartOfDay().toDate();
		DateParam dateParam = new DateParam(dateFormat.format(nowMidnight));
		Weather testWeather = new Weather();
		
		// set-up
		Mockito.when(weatherDao.getWeather(venueId, nowMidnight)).thenReturn(null);
		Mockito.when(catchRecordService.getVenue(venueId)).thenReturn(venue);
		Mockito.when(weatherService.getWeather(location, nowMidnight)).thenReturn(testWeather);
		
		// do
		Weather weather = weatherEndPoint.getWeather(venueId, dateParam);
		
		// test
		Assert.assertThat(weather, notNullValue());
		Assert.assertThat(weather, equalTo(testWeather));
		Mockito.verify(weatherDao, Mockito.times(1)).getWeather(venueId, nowMidnight);
		Mockito.verify(weatherDao, Mockito.times(1)).saveWeather(testWeather);
	}

}
