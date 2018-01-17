package com.codecrocodile.dao.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codecrocodile.club.model.Post;
import com.codecrocodile.club.model.PostComment;

public interface NoticeBoardMapper {
	
	public int getPostCount(@Param("accountId") int accountId);
	

	public Long getPostUserId(@Param("postId") long postId);
	
	public void insertPost(Post post);
	
	public void updatePost(Post post);
	
	public List<Post> getPosts(@Param("accountId") long accountId, @Param("skipRows") int skipRows, @Param("pageSize") int pageSize);
	
	public void retirePost(@Param("postId") long postId);
	
	
	public Long getPostCommentUserId(@Param("postCommentId") long postCommentId);
	
	public void insertPostComment(PostComment postComment);
	
	public void updatePostComment(PostComment postComment);
	
	public void retirePostComment(@Param("postCommentId") long postCommentId);
	
}
