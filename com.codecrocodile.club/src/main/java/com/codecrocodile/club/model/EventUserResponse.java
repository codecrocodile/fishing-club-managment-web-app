package com.codecrocodile.club.model;

import java.util.Date;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EventUserResponse {
	
	private long eventResponseId;
	
	private long eventId;
	
	private Date date;
	
	private String response;
	
	private String comment;
	
	private int userId;
	
	private String userName;

	
	public EventUserResponse() {
		super();
	}

	public long getEventReponseId() {
		return eventResponseId;
	}

	public void setEventReponseId(long eventResponseId) {
		this.eventResponseId = eventResponseId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	
}
