package com.codecrocodile.club.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.codecrocodile.club.model.lookup.Venue;
import com.codecrocodile.club.security.SecurityContextFacade;
import com.codecrocodile.dao.mybatis.mappers.LookupMapper;


@Service("LookupService") // @Service annotation is to identify that it�s a Spring component that provides business services to another layer 
@Repository // @Repository annotation indicates that the class contains data access logic and instructs Spring to translate the vendor-specific exceptions to Spring�s DataAccessException hierarchy
public class LookupService {

	@Autowired
	private SecurityContextFacade securityContextFacade;
	
	@Autowired
	private LookupMapper lookupMapper;
	
	
	@RolesAllowed("ROLE_USER")
    public List<Venue> getVenues() {
		return lookupMapper.getVenues(securityContextFacade.getCurrentAccountId());
    }
}
