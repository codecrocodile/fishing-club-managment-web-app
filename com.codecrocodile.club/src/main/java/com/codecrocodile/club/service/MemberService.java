package com.codecrocodile.club.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecrocodile.club.model.Member;
import com.codecrocodile.club.security.SecurityContextFacade;
import com.codecrocodile.dao.mybatis.mappers.MemberMapper;

@Service("MemberService") // @Service annotation is to identify that it�s a Spring component that provides business services to another layer 
@Repository // @Repository annotation indicates that the class contains data access logic and instructs Spring to translate the vendor-specific exceptions to Spring�s DataAccessException hierarchy
@Transactional
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SecurityContextFacade securityContextFacade;
	
    
	@RolesAllowed("ROLE_USER")
	@Transactional(readOnly=true)
    public Member getAuthenticatedMember() {
		return memberMapper.getMember(securityContextFacade.getCurrentUserId());
    }
	
	@RolesAllowed("ROLE_USER")
	@Transactional
	public void saveAuthenticatedMember(Member member) {
		// @:security just in case a user tries to hack it then only allow them to update there own record
		member.setUserId(securityContextFacade.getCurrentUserId());
		memberMapper.updateSafeMemberDetails(member);
	}

	/**
	 * Returns the members of the same account as logged in user (who will be an admin user).
	 */
	@RolesAllowed("ROLE_ADMIN")
	@Transactional(readOnly=true)
	public List<Member> getMembers() {
		return memberMapper.getMembers(securityContextFacade.getCurrentAccountId());
	}

	@RolesAllowed("ROLE_ADMIN")
	public Member saveMember(Member member) {
		if (member.getUserId() == 0) {
			
			// TODO go and generate password, email the new member to say that an account has been setup, etc...
			
			// @:security to ensure that they don't try inserting into another account
			member.setAccountId(securityContextFacade.getCurrentAccountId()); 
			memberMapper.insertMember(member);
		} else {
			// @:security to ensure that they don't changing another accounts data
			boolean memberOfAccount = memberMapper.isMemberOfAccount(securityContextFacade.getCurrentAccountId(), member.getUserId());
			if (memberOfAccount) {
				memberMapper.updateMember(member);
			}
		}
		
		return member;
	}

	@RolesAllowed("ROLE_ADMIN")
	public void deleteMember(int userId) {
		// @:security to ensure that they don't changing another accounts data
		boolean memberOfAccount = memberMapper.isMemberOfAccount(securityContextFacade.getCurrentAccountId(), userId);
		if (memberOfAccount) {
			memberMapper.retireMember(userId);
		}
	}

}
