package com.codecrocodile.club.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codecrocodile.club.common.util.JsonDateDeserializer;
import com.codecrocodile.club.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Post {
	
	private long postId;
	
	private long accountId;
	
	private long userId;
	
	private String userName;
	
	private String userImage;
	
	private Date date;
	
	private String text;
	
	private boolean retired;
	
	private List<PostComment> postComments = new ArrayList<>();
	
	
	public Post() {
		super();
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getAccountId() {
		return accountId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
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

	public List<PostComment> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<PostComment> postComments) {
		this.postComments = postComments;
	}

}
