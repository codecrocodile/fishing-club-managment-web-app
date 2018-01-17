package com.codecrocodile.club.model.catchrecord;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity()
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="catchrecorditem") 
@Access(AccessType.FIELD)
public class CatchRecordItem {
	
	@Id
	@Column(name="catchRecordItemId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="catchRecordId", nullable=false)
	@Transient
	private long catchRecordId;
	
	@Column(name="venueId")
	private long venueId;
	
	@Column(name="lon")
	private double lon;
	
	@Column(name="lat")
	private double lat;
	
	@Column(name="timePeriod", nullable=false)
	private String timePeriod;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="killed")
	private boolean killed;

	
	public CatchRecordItem() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCatchRecordId() {
		return catchRecordId;
	}

	public void setCatchRecordId(long catchRecordId) {
		this.catchRecordId = catchRecordId;
	}
	
	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public boolean isKilled() {
		return killed;
	}

	public void setKilled(boolean killed) {
		this.killed = killed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatchRecordItem other = (CatchRecordItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
