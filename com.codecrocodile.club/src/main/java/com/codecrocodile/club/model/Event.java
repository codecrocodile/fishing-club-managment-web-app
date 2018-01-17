package com.codecrocodile.club.model;

import java.util.Date;
import java.util.List;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Event {
	
	private long eventId;
	
	private int accountId;
	
	private Date date;
	
	private Date eventDate;
	
	private String eventTime;
	
	private String name;
	
	private String location;
	
	private String description;
	
	private String status;
	
	private int userId;
	
	private String userName;
	
	private List<EventUserResponse> eventUserResponses;
	

	public Event() {
		super();
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEventDate() {
		return eventDate;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<EventUserResponse> getEventUserResponses() {
		return eventUserResponses;
	}

	public void setEventUserResponses(List<EventUserResponse> eventUserResponses) {
		this.eventUserResponses = eventUserResponses;
	}
	
}
