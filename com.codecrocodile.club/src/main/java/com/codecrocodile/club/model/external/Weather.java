package com.codecrocodile.club.model.external;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="weather") 
@Access(AccessType.FIELD)
public class Weather {
	
	@Id
	@Column(name="weatherId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long weatherId;
	
	@Column(name="venueId")
	private long venueId;

	@Column(name="date")
	private Date date;
	
	@Column(name="maxtempC")
	private int maxtempC;
	
	@Column(name="maxtempF")
	private int maxtempF;
	
	@Column(name="mintempC")
	private int mintempC;
	
	@Column(name="mintempF")
	private int mintempF;

	@JsonProperty("hourly")
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="weatherId", nullable=false)
	private List<WeatherHourly> weatherHourlyList = new ArrayList<>();
	
	
	public Weather() {
		super();
	}
	
	public long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(long weatherId) {
		this.weatherId = weatherId;
	}

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMaxtempC() {
		return maxtempC;
	}

	public void setMaxtempC(int maxtempC) {
		this.maxtempC = maxtempC;
	}

	public int getMaxtempF() {
		return maxtempF;
	}

	public void setMaxtempF(int maxtempF) {
		this.maxtempF = maxtempF;
	}

	public int getMintempC() {
		return mintempC;
	}

	public void setMintempC(int mintempC) {
		this.mintempC = mintempC;
	}

	public int getMintempF() {
		return mintempF;
	}

	public void setMintempF(int mintempF) {
		this.mintempF = mintempF;
	}

	public List<WeatherHourly> getWeatherHourlyList() {
		return weatherHourlyList;
	}

	public void setWeatherHourlyList(List<WeatherHourly> weatherHourlyList) {
		this.weatherHourlyList = weatherHourlyList;
	}

}
