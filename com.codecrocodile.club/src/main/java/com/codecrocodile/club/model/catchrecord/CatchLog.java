package com.codecrocodile.club.model.catchrecord;

import java.util.Date;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CatchLog {
	
	private long catchRecordId;
	
	private Date date;
	
	private String waters;
	
	private String notes;
	
	private int numberOfFish;
	
	private String memberName;
	
	
	public CatchLog() {
		super();
	}

	public long getCatchRecordId() {
		return catchRecordId;
	}

	public void setCatchRecordId(long catchRecordId) {
		this.catchRecordId = catchRecordId;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public String getWaters() {
		return waters;
	}

	public void setWaters(String waters) {
		this.waters = waters;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getNumberOfFish() {
		return numberOfFish;
	}

	public void setNumberOfFish(int numberOfFish) {
		this.numberOfFish = numberOfFish;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

}
