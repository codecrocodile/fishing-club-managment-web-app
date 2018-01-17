package com.codecrocodile.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.lookup.Venue;

public interface LookupMapper {
	
	public List<Venue> getVenues(@Param("accountId") int accountId);

}
