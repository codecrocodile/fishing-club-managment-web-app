package com.codecrocodile.club.service;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.codecrocodile.club.common.UnauthorisedException;
import com.codecrocodile.club.model.Post;
import com.codecrocodile.club.model.PostComment;
import com.codecrocodile.club.security.MemberDetails;
import com.codecrocodile.dao.mybatis.mappers.NoticeBoardMapper;

@Service("DashBoardService")  
@Repository
public class DashBoardService {
	
	public static final int pageSize = 8; // must keep in sync with value on client
	
    @Autowired
    private NoticeBoardMapper noticeBoardMapper;
	
    
    @RolesAllowed("ROLE_USER")
	public List<Post> getPosts(int skipRows) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		List<Post> posts = noticeBoardMapper.getPosts(memberDetails.getAccountId(), skipRows, DashBoardService.pageSize);
		
		return posts;
	}

    @RolesAllowed("ROLE_USER")
	public Post savePost(Post post) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		// TODO fix and finish
		post.setAccountId(memberDetails.getAccountId());
		post.setUserId(memberDetails.getUserId());
		post.setUserName("Test Change This");
		post.setDate(new Date());
		
		
		Long postUserId = this.noticeBoardMapper.getPostUserId(post.getPostId());
		
		if (postUserId != null) {
			if (postUserId.longValue() == memberDetails.getUserId()) {
				this.noticeBoardMapper.updatePost(post);
			} else {
				throw new UnauthorisedException("Comment doesn't belong to you. Therefore you can't update.");
			}
		} else {
			noticeBoardMapper.insertPost(post);
		}
		
		return post;
	}

    @RolesAllowed("ROLE_USER")
	public void deletePost(int postId) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		boolean commentBelongsToUser = false;
		Long postUserId = this.noticeBoardMapper.getPostUserId(postId);
		if (postUserId != null && postUserId.longValue() == memberDetails.getUserId()) {
			commentBelongsToUser = true;
		}
		
    	if (commentBelongsToUser == true) {
    		this.noticeBoardMapper.retirePost(postId);
    	} else {
    		throw new UnauthorisedException("Post doesn't belong to you. Therefore you can't delete.");
    	}
	}

    @RolesAllowed("ROLE_USER")
	public void deletePostComment(long postCommentId) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
    	
		boolean commentBelongsToUser = false;
		Long postCommentUserId = this.noticeBoardMapper.getPostCommentUserId(postCommentId);
		if (postCommentUserId != null && postCommentUserId.longValue() == memberDetails.getUserId()) {
			commentBelongsToUser = true;
		}
		
    	if (commentBelongsToUser == true) {
    		this.noticeBoardMapper.retirePostComment(postCommentId);
    	} else {
    		throw new UnauthorisedException("Comment doesn't belong to you. Therefore you can't delete.");
    	}
	}

    @RolesAllowed("ROLE_USER")
	public int getTotalCount() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
		
		return this.noticeBoardMapper.getPostCount(memberDetails.getAccountId());
	}

	@RolesAllowed("ROLE_USER")
	public PostComment savePostComment(PostComment postComment) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

		postComment.setUserId(memberDetails.getUserId());
		postComment.setUserName("Test Change This");
		postComment.setUserImage("");
		postComment.setDate(new Date());
		
		Long postCommentUserId = this.noticeBoardMapper.getPostCommentUserId(postComment.getPostCommentId());
		if (postCommentUserId != null) {
			if (postCommentUserId.longValue() == memberDetails.getUserId()) {
				this.noticeBoardMapper.updatePostComment(postComment);
			} else {
				throw new UnauthorisedException("Comment doesn't belong to you. Therefore you can't update.");
			}
		} else {
			this.noticeBoardMapper.insertPostComment(postComment);
		}
		
		return postComment;
	}

}
