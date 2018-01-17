package com.codecrocodile.dao.mybatis.mappers;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.external.Weather;

public interface WeatherMapper {
	
	public Weather getWeather(@Param("venueId") long venueId, @Param("date") Date date);
	
	public Weather insertWeather(Weather weather);
	
	public void updateWeather(Weather weather);

}
