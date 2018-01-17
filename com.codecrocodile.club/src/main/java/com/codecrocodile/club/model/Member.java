package com.codecrocodile.club.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity()
@Table(name = "user")
@Access(AccessType.FIELD)
public class Member {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private int accountId;

	@Column(name = "title")
	private String title;

	@Column(name = "forename")
	private String forename;

	@Column(name = "surname")
	private String surname;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "address3")
	private String address3;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "homePhone")
	private String homePhone;

	@Column(name = "mobilePhone")
	private String mobilePhone;

	@Column(name = "email")
	private String email;

	@Column(name = "photo")
	private String photo;

	private String photoSource;

	private String photoThumb;

	private String password;

	private Date startDate;

	private Date suspendedDate;

	private Date retiredDate;

	private int membershipTypeId;

	public Member() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDob() {
		return dob;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPhotoSource() {
		return photoSource;
	}

	public void setPhotoSource(String photoSource) {
		this.photoSource = photoSource;
	}

	public String getPhotoThumb() {
		return photoThumb;
	}

	public void setPhotoThumb(String photoThumb) {
		this.photoThumb = photoThumb;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getSuspendedDate() {
		return suspendedDate;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setSuspendedDate(Date suspendedDate) {
		this.suspendedDate = suspendedDate;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getRetiredDate() {
		return retiredDate;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setRetiredDate(Date retiredDate) {
		this.retiredDate = retiredDate;
	}

	public int getMembershipTypeId() {
		return membershipTypeId;
	}

	public void setMembershipTypeId(int membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	@Override
	public boolean equals(Object obj) {
		return this.userId == ((Member) obj).getUserId();
	}
}
