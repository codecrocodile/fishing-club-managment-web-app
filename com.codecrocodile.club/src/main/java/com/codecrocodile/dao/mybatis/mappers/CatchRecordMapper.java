package com.codecrocodile.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.catchrecord.CatchLog;
import com.codecrocodile.club.model.catchrecord.CatchRecord;
import com.codecrocodile.club.model.catchrecord.CatchRecordItem;
import com.codecrocodile.club.model.lookup.Venue;

public interface CatchRecordMapper {

	public Venue getVenue(@Param("venueId") long venueId);
	
	public CatchRecord getCatchRecord(@Param("userId") int userId, @Param("date") Date date);
	
	public List<CatchRecordItem> getCatchRecordItemForGetCatchRecord(@Param("catchRecordId") long catchRecordId);
	
	public void insertCatchRecord(CatchRecord catchRecord);
	
	public void insertCatchRecordItem(CatchRecordItem catchRecordItem);
	
	public void updateCatchRecord(CatchRecord catchRecord);
	
	public void updateCatchRecordItem(CatchRecordItem catchRecordItem);
	
	public void deleteOrphanCatchRecordItem(List<Long> ids);

	public int getCatchLogTotalCount(@Param("accountId") int accountId);

	public List<CatchLog> getCatchLogs(@Param("accountId") int accountId, @Param("skipRows") int skipRows, @Param("pageSize") int pageSize);
	
}
