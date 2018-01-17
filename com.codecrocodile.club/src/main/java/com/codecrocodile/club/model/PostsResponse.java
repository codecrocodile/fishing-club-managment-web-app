package com.codecrocodile.club.model;

import java.util.ArrayList;
import java.util.List;

public class PostsResponse {
	
	private List<Post> posts = new ArrayList<>();
	
	private int totalCount;
	
	
	public PostsResponse() {
		super();
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
