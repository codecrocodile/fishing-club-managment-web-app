package com.codecrocodile.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.Member;

public interface MemberMapper {

	/**
	 * Gets a single given member.
	 */
	public Member getMember(@Param("userId") int userId);

	/**
	 * Gets all members in a given account.
	 */
	public List<Member> getMembers(@Param("accountId") int accountId);
	
	/**
	 * Inserts a new member.
	 */
	public void insertMember(Member member);
	
	/**
	 * Full update of the member fields allowed by admin user. 
	 */
	public void updateMember(Member member);
	
	/**
	 * Partial update of the member field allowed by a normal user. 
	 */
	public void updateSafeMemberDetails(Member member);
	
	/**
	 * Retires a given member. This effectively deletes the member as far as the user is concerned.
	 */
	public void retireMember(@Param("userId") int userId);
	
	/**
	 * This is a way of checking that a user is in the same account as the person updating the user
	 * details. This is just in case someone tries of overwrite another clubs data.
	 */
	public boolean isMemberOfAccount(@Param("accountId") int accountId, @Param("userId") int userId);
	
}
