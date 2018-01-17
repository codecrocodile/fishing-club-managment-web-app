package com.codecrocodile.club.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.codecrocodile.club.model.accounting.Invoice;
import com.codecrocodile.club.security.SecurityContextFacade;
import com.codecrocodile.dao.mybatis.mappers.AccountingMapper;

@Service("AccountingService") 
@Repository 
public class AccountingService {
	
	@Autowired
	private SecurityContextFacade securityContextFacade;
	
	@Autowired
	private AccountingMapper accountingMapper;

	
	@RolesAllowed("ROLE_USER")
	public List<Invoice> authenticatedMemberInvoices() {
		return accountingMapper.getMemberInvoices(securityContextFacade.getCurrentUserId());
    }

	@RolesAllowed("ROLE_ADMIN")
	public List<Invoice> getInvoicesForMember(int userId) {
		return accountingMapper.getMemberInvoices(userId);
	}
}
