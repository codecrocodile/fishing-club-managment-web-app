package com.codecrocodile.club.model.external;

import java.util.ArrayList;
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
@Table(name="weatherhourly") 
@Access(AccessType.FIELD)
public class WeatherHourly {
	
	@Id
	@Column(name="weatherHourlyId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long weatherHourlyId;
	
	@Column(name="weatherId", insertable=false, updatable=false)
	private long weatherId;

	@JsonProperty("FeelsLikeC")
	@Column(name="feelsLikeC")
	private int feelsLikeC;
    
	@JsonProperty("FeelsLikeF")
	@Column(name="feelsLikeF")
	private int feelsLikeF;
	
	/** Percentage cloud cover */
	@Column(name="cloudcover")
	private int cloudcover;
	
	/** Percentage humidity */
	@Column(name="humidity")
	private int humidity;
	
	@Column(name="precipMM")
	private double precipMM;
	
	@Column(name="pressure")
	private int pressure;
	
	@Column(name="tempC")
	private int tempC;
	
	@Column(name="tempF")
	private int tempF;
	
	@Column(name="visibility")
	private int visibility;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="weatherHourlyId")
	private List<WeatherDesc> weatherDesc = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="weatherHourlyId")
	private List<WeatherIconUrl> weatherIconUrl = new ArrayList<>();
	
	/** Wind direction in 16-point compass i.e. N, NNE, NE, ENE, E, etc... */
	@Column(name="winddir16Point")
	private String winddir16Point;
	
	@Column(name="winddirDegree")
	private int winddirDegree;
    
	@Column(name="windspeedKmph")
	private int windspeedKmph;
	
	@Column(name="windspeedMiles")
	private int windspeedMiles;
	
	
	public WeatherHourly() {
		super();
	}
	
	public long getWeatherHourlyId() {
		return weatherHourlyId;
	}

	public void setWeatherHourlyId(long weatherHourlyId) {
		this.weatherHourlyId = weatherHourlyId;
	}

	public long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(long weatherId) {
		this.weatherId = weatherId;
	}

	public int getFeelsLikeC() {
		return feelsLikeC;
	}

	public void setFeelsLikeC(int feelsLikeC) {
		this.feelsLikeC = feelsLikeC;
	}

	public int getFeelsLikeF() {
		return feelsLikeF;
	}

	public void setFeelsLikeF(int feelsLikeF) {
		this.feelsLikeF = feelsLikeF;
	}

	public int getCloudcover() {
		return cloudcover;
	}

	public void setCloudcover(int cloudcover) {
		this.cloudcover = cloudcover;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getPrecipMM() {
		return precipMM;
	}

	public void setPrecipMM(double precipMM) {
		this.precipMM = precipMM;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getTempC() {
		return tempC;
	}

	public void setTempC(int tempC) {
		this.tempC = tempC;
	}

	public int getTempF() {
		return tempF;
	}

	public void setTempF(int tempF) {
		this.tempF = tempF;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
	public List<WeatherDesc> getWeatherDesc() {
		return weatherDesc;
	}

	public void setWeatherDesc(List<WeatherDesc> weatherDesc) {
		this.weatherDesc = weatherDesc;
	}

	public List<WeatherIconUrl> getWeatherIconUrl() {
		return weatherIconUrl;
	}

	public void setWeatherIconUrl(List<WeatherIconUrl> weatherIconUrl) {
		this.weatherIconUrl = weatherIconUrl;
	}

	public String getWinddir16Point() {
		return winddir16Point;
	}

	public void setWinddir16Point(String winddir16Point) {
		this.winddir16Point = winddir16Point;
	}

	public int getWinddirDegree() {
		return winddirDegree;
	}

	public void setWinddirDegree(int winddirDegree) {
		this.winddirDegree = winddirDegree;
	}

	public int getWindspeedKmph() {
		return windspeedKmph;
	}

	public void setWindspeedKmph(int windspeedKmph) {
		this.windspeedKmph = windspeedKmph;
	}

	public int getWindspeedMiles() {
		return windspeedMiles;
	}

	public void setWindspeedMiles(int windspeedMiles) {
		this.windspeedMiles = windspeedMiles;
	}
	
}
