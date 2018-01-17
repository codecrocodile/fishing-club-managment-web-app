package com.codecrocodile.club.model;

import java.util.List;

public class EventResponse {
	
	private List<Event> events;
	
	private int totalCount;

	
	public EventResponse() {
		super();
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
