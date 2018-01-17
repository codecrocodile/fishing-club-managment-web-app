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
@Table(name="weatherdesc") 
@Access(AccessType.FIELD)
public class WeatherDesc {

	@Id
	@Column(name="weatherDescId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long weatherDescId;
	
	@Column(name="weatherHourlyId")
	private long weatherHourlyId;
	
	@Column(name="description")
	private String value;
	
	
	public WeatherDesc() {
		super();
	}

	public long getWeatherDescId() {
		return weatherDescId;
	}

	public void setWeatherDescId(long weatherDescId) {
		this.weatherDescId = weatherDescId;
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
