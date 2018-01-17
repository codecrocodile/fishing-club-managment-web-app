package com.codecrocodile.club.model.catchrecord;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity()
@Table(name="catchrecord") 
@Access(AccessType.FIELD)
public class CatchRecord {
	
	@Id
	@Column(name="catchRecordId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="userId")
	private long userId;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="fishingNotes")
	private String fishingNotes;
	
	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="catchRecordId", referencedColumnName="catchRecordId", nullable=false, updatable=false)
	private List<CatchRecordItem> catchRecordItems;

	
	public CatchRecord() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}
	
	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public String getFishingNotes() {
		return fishingNotes;
	}

	public void setFishingNotes(String fishingNotes) {
		this.fishingNotes = fishingNotes;
	}

	public List<CatchRecordItem> getCatchRecordItems() {
		return catchRecordItems;
	}

	public void setCatchRecordItems(List<CatchRecordItem> catchRecordItems) {
		this.catchRecordItems = catchRecordItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatchRecord other = (CatchRecord) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
