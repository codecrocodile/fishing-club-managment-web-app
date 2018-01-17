package com.codecrocodile.club.model.external;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="weathericonurl") 
@Access(AccessType.FIELD)
public class WeatherIconUrl {
	
	@Id
	@Column(name="weatherIconUrlId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long weatherIconUrlId;
	
	@Column(name="weatherHourlyId")
	private long weatherHourlyId;

	@Column(name="url")
	private String value;
	

	public WeatherIconUrl() {
		super();
	}

	public long getWeatherIconUrlId() {
		return weatherIconUrlId;
	}

	public void setWeatherIconUrlId(long weatherIconUrlId) {
		this.weatherIconUrlId = weatherIconUrlId;
	}

	public long getWeatherHourlyId() {
		return weatherHourlyId;
	}

	public void setWeatherHourlyId(long weatherHourlyId) {
		this.weatherHourlyId = weatherHourlyId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
