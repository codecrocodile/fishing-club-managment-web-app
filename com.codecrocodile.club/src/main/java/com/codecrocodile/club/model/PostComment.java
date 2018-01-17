package com.codecrocodile.club.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PostComment {
	
	private long postCommentId;
	
	private long postId;
	
	private long userId;
	
	private String userName;
	
	private String userImage;
	
	private Date date;
	
	@Length(min=20, max=500, message="Comment is not the correct size")
	private String text;
	
	private boolean retired;
	
	
	public PostComment() {
		super();
	}

	public long getPostCommentId() {
		return postCommentId;
	}

	public void setPostCommentId(long postCommentId) {
		this.postCommentId = postCommentId;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}
	
	@JsonDeserialize(using=JsonDateDeserializer.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isRetired() {
		return retired;
	}

	public void setRetired(boolean retired) {
		this.retired = retired;
	}
	
}
