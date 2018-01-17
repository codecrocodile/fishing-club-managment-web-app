package com.codecrocodile.club.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.MutableDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.codecrocodile.club.model.external.Weather;
import com.codecrocodile.club.model.external.WeatherException;
import com.codecrocodile.club.model.external.WeatherHourly;

@Ignore
public class WeatherServiceTest {

	public WeatherService weatherService;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		this.weatherService = new WeatherService();
	}
	
	@After
	public void tearDown() {
		this.weatherService = null; // actually I think this is done automaticly
	}
	
	@Test
	public void testResponseConversionForOneDay() throws Exception {
		InputStream resourceAsStream = this.getClass().getResourceAsStream("ExampleOneDayWeatherResponse.txt");
		String json = IOUtils.toString(resourceAsStream).replace("\n","");
		resourceAsStream.close();//TODO wrap and close automaticly
		
		List<Weather> weathers = this.weatherService.convertResponse(json);
		
		assertNotNull("Should never return null", weathers);
		assertThat("Should have returned on weather report", weathers.size(), is(equalTo(1)));
		
		Weather weather = weathers.get(0);
		
		Calendar testDateCal = Calendar.getInstance();
		testDateCal.set(Calendar.YEAR, 2014);
		testDateCal.set(Calendar.MONTH, Calendar.NOVEMBER);
		testDateCal.set(Calendar.DAY_OF_MONTH, 27);
		testDateCal.set(Calendar.HOUR_OF_DAY, 0);
		testDateCal.set(Calendar.MINUTE, 0);
		testDateCal.set(Calendar.SECOND, 0);
		testDateCal.set(Calendar.MILLISECOND, 0);
		assertThat("Dates do not match. They should match to the millisecond.", weather.getDate(), is(equalTo(testDateCal.getTime())));
		
		assertThat(weather.getMaxtempC(), is(equalTo(10)));
		assertThat(weather.getMaxtempF(), is(equalTo(50)));
		assertThat(weather.getMintempC(), is(equalTo(6)));
		assertThat(weather.getMintempF(), is(equalTo(43)));
		assertNotNull("Should never return null", weather.getWeatherHourlyList());
		assertThat("Expected the average weather for the day, and therfore 1 weather report.", weather.getWeatherHourlyList().size(), is(equalTo(1)));
		
		WeatherHourly weatherHourly = weather.getWeatherHourlyList().get(0);
		assertEquals(8, weatherHourly.getFeelsLikeC());
		assertEquals(47, weatherHourly.getFeelsLikeF());
		assertEquals(40, weatherHourly.getCloudcover());
		assertEquals(89, weatherHourly.getHumidity());
		assertEquals(2.5, weatherHourly.getPrecipMM(), 0);
		assertEquals(1009, weatherHourly.getPressure());
		assertEquals(10, weatherHourly.getTempC());
		assertEquals(50, weatherHourly.getTempF());
		assertEquals(9, weatherHourly.getVisibility());
		assertEquals("W", weatherHourly.getWinddir16Point());
		assertEquals(261, weatherHourly.getWinddirDegree());
		assertEquals(13, weatherHourly.getWindspeedKmph());
		assertEquals(8, weatherHourly.getWindspeedMiles());
		
		assertNotNull("Should never return null", weatherHourly.getWeatherDesc());
		assertEquals(1, weatherHourly.getWeatherDesc().size());
		assertThat(weatherHourly.getWeatherDesc().get(0).getValue(), is(equalTo("Patchy light rain")));
		assertNotNull("Should never return null", weatherHourly.getWeatherIconUrl());
		assertEquals(1, weatherHourly.getWeatherIconUrl().size());
		assertThat(weatherHourly.getWeatherIconUrl().get(0).getValue(), is(equalTo("http://cdn.worldweatheronline.net/images/wsymbols01_png_64/wsymbol_0017_cloudy_with_light_rain.png")));
	}
	
	@Test
	public void testResponseConversionFiveDays() throws Exception {
		InputStream resourceAsStream = this.getClass().getResourceAsStream("ExampleFiveDayWeatherResponse.txt");
		String json = IOUtils.toString(resourceAsStream).replace("\n","");
		
		List<Weather> weathers = this.weatherService.convertResponse(json);
		
		assertNotNull("Should never return null", weathers);
		assertThat("Should have returned five weather reports", weathers.size(), is(equalTo(5)));
	}
	
	@Test
	@Ignore // as gives print stack trace that I don't wan't to see
	public void testResponseConversionFailure() throws Exception {
		List<Weather> weathers = this.weatherService.convertResponse("");
		
		assertNotNull("Should never return null", weathers);
		assertThat("Should have returned zero weather reports", weathers.size(), is(equalTo(0)));
	}

	@Test
	public void testValidCall() throws Exception {
		DateTime nowDt = DateTime.now().withTimeAtStartOfDay();
		Weather weather = this.weatherService.getWeather("G74LL", nowDt.toDate());
		
		assertNotNull("Expected Weather object, but was null.", weather);
		assertThat(weather.getDate(), equalTo(nowDt.toDate()));
	}
	
	@Test
	public void testValidCallLowerDateBoundary() throws Exception {
		DateTime nowDt = DateTime.now().withTimeAtStartOfDay();
		MutableDateTime pastDt = nowDt.toMutableDateTime();
		pastDt.addDays(-60);
		
		Weather weather = this.weatherService.getWeather("G74LL", pastDt.toDate());
		assertNotNull("Expected Weather object, but was null.", weather);
		
		DateTimeComparator dateComparator = DateTimeComparator.getDateOnlyInstance();
		assertThat(dateComparator.compare(weather.getDate(), pastDt.toDate()), equalTo(0));
	}
	
	@Test
	public void testValidCallUpperDateBoundary() throws Exception {
		DateTime nowDt = DateTime.now().withTimeAtStartOfDay();
		MutableDateTime pastDt = nowDt.toMutableDateTime();
		pastDt.addDays(4);
		
		Weather weather = this.weatherService.getWeather("G74LL", pastDt.toDate());
		assertNotNull("Expected Weather object, but was null.", weather);
		
		DateTimeComparator dateComparator = DateTimeComparator.getDateOnlyInstance();
		assertThat(dateComparator.compare(weather.getDate(), pastDt.toDate()), equalTo(0));
	}
	
	@Test
	public void testInvalidVenueCall() throws Exception {
		DateTime nowDt = DateTime.now().withTimeAtStartOfDay();
		
		thrown.expect(WeatherException.class);
		// not testing the error message from weather service as we have no control over that
		
		this.weatherService.getWeather("BADLOCATION", nowDt.toDate());
	}
	
	@Test
	public void testInvalidDateInPastCall() throws Exception {
		DateTime nowDt = DateTime.now().withTimeAtStartOfDay();
		MutableDateTime pastDt = nowDt.toMutableDateTime();
		pastDt.addDays(-61);
		
		thrown.expect(WeatherException.class);
		thrown.expectMessage("Date too far in past. 60 days in past allowed");
		
		this.weatherService.getWeather("G74LL", pastDt.toDate());
	}
	
	@Test
	public void testInvalidDateInFutureCall() throws Exception {
		DateTime nowDt = DateTime.now().withTimeAtStartOfDay();
		MutableDateTime futureDt = nowDt.toMutableDateTime();
		futureDt.addDays(5);
		
		thrown.expect(WeatherException.class);
		thrown.expectMessage("Date too far in future. 4 days in future allowed");
	        
		this.weatherService.getWeather("G74LL", futureDt.toDate());
	}

}
