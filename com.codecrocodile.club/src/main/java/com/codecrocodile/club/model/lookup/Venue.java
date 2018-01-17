package com.codecrocodile.club.model.lookup;


public class Venue {
	
	private long venueId;
	
	private int accountId;
	
	private String description;
	
	private double lon;
	
	private double lat;

	private int zoom;
	
	
	public Venue() {
		super();
	}

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

}
