package com.codecrocodile.club.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecrocodile.club.model.Post;
import com.codecrocodile.club.model.catchrecord.CatchLog;
import com.codecrocodile.club.model.catchrecord.CatchRecord;
import com.codecrocodile.club.model.catchrecord.CatchRecordItem;
import com.codecrocodile.club.model.lookup.Venue;
import com.codecrocodile.club.security.MemberDetails;
import com.codecrocodile.dao.mybatis.mappers.CatchRecordMapper;

@Service("CatchRecordService") // @Service annotation is to identify that it�s a Spring component that provides business services to another layer 
@Repository // @Repository annotation indicates that the class contains data access logic and instructs Spring to translate the vendor-specific exceptions to Spring�s DataAccessException hierarchy
public class CatchRecordService {
	
	private static final int CATCH_LOG_PAGE_SIZE = 10;
	
    @Autowired
    private CatchRecordMapper catchRecordMapper;
    
	@RolesAllowed("ROLE_USER")
    public CatchRecord getCatchRecord(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		CatchRecord catchRecord = catchRecordMapper.getCatchRecord(memberDetails.getUserId(), cal.getTime());
		
		return catchRecord;
    }
	
	@RolesAllowed("ROLE_USER")
	@Transactional
	public void saveCatchRecord(CatchRecord catchRecord) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		catchRecord.setUserId(memberDetails.getUserId());
		// TODO validate
		// user can only save to venue they have access
		// user can't save more than 50 catch records (in case of hacks)
		// can't be date in the future
		// varchar lengths
		// catch periods are valid (or leave to the db to verify)
		
    	if (catchRecord.getId() < 1) {
    		this.insert(catchRecord);
    	} else {
    		this.update(catchRecord);
    	}
	}
	
	private CatchRecord insert(CatchRecord catchRecord) {
		catchRecordMapper.insertCatchRecord(catchRecord);
		long catchRecordId = catchRecord.getId();
		if (catchRecord.getCatchRecordItems() != null) {
			for (CatchRecordItem cri : catchRecord.getCatchRecordItems()) {
				cri.setCatchRecordId(catchRecordId);
				catchRecordMapper.insertCatchRecordItem(cri);
			}
		}
		
		return catchRecord;
	}
	
	private CatchRecord update(CatchRecord catchRecord) {
		//TODO how to guard against somone deleting or updating someone elses records
		
		catchRecordMapper.updateCatchRecord(catchRecord);
		long id = catchRecord.getId();
		
		List<Long> ids = new ArrayList<Long>();
		List<CatchRecordItem> catchRecordItemForGetCatchRecord = catchRecordMapper.getCatchRecordItemForGetCatchRecord(id);
		for (CatchRecordItem i : catchRecordItemForGetCatchRecord) {
			ids.add(i.getId());
		}
		
		if (catchRecord.getCatchRecordItems() != null) {
			for(CatchRecordItem cri : catchRecord.getCatchRecordItems()) {
				if (cri.getId() < 1) {
					cri.setCatchRecordId(id); // security: don't let them update ones that are not their own
					catchRecordMapper.insertCatchRecordItem(cri);
				} else {
					cri.setCatchRecordId(id); // security: don't let them update ones that are not their own
					catchRecordMapper.updateCatchRecordItem(cri);
					ids.remove(cri.getId());
				}
			}
		}
		
		if (ids.size() > 0) {
			catchRecordMapper.deleteOrphanCatchRecordItem(ids);
		}
		
		return catchRecord;
	}
	

	public Venue getVenue(long venueId) {
		return catchRecordMapper.getVenue(venueId);
	}

	public List<CatchLog> getCatchLogs(int skipRows) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		List<CatchLog> catchLogs = catchRecordMapper.getCatchLogs(memberDetails.getAccountId(), skipRows, CatchRecordService.CATCH_LOG_PAGE_SIZE);
		
		return catchLogs;
	}

	public int getCatchLogTotalCount() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return catchRecordMapper.getCatchLogTotalCount(memberDetails.getAccountId());
	}
}
