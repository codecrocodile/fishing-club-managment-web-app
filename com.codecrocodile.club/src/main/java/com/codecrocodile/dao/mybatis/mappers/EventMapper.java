package com.codecrocodile.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.Event;
import com.codecrocodile.club.model.EventUserResponse;

public interface EventMapper {
	
	public int getEventCount(@Param("accountId") int accountId);
	
	public void insertEvent(Event event);
	
	public void updateEvent(Event event);
	
	public List<Event> getEvents(@Param("accountId") long accountId, @Param("skipRows") int skipRows, @Param("pageSize") int pageSize);
	
	public void retireEvent(@Param("eventId") long eventId, @Param("accountId") int accountId);
	
	public void insertEventResponse(EventUserResponse eventResponse);

	public boolean isEventExists(@Param("eventId") long eventId, @Param("accountId") int accountId);
	
}
