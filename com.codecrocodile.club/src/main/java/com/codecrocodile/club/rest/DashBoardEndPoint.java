package com.codecrocodile.club.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codecrocodile.club.common.ExceptionMapperImpl;
import com.codecrocodile.club.model.Post;
import com.codecrocodile.club.model.PostComment;
import com.codecrocodile.club.model.PostsResponse;
import com.codecrocodile.club.service.DashBoardService;

@Path("/dashboard-service")
@Controller
public class DashBoardEndPoint {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	
	@GET
	@Path("/post")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPosts(
			@QueryParam(value = "page") int page, 
			@QueryParam(value = "start") int start, 
			@QueryParam(value = "limit") int limit) {
		
		PostsResponse postsResponse = new PostsResponse();
		postsResponse.setPosts(dashBoardService.getPosts(start));
		postsResponse.setTotalCount(dashBoardService.getTotalCount());
		
		return Response.ok(postsResponse).build();
	}
	
	@POST
	@Path("/post/{postId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Post postPost(Post post) {
		return dashBoardService.savePost(post);
	}
	
	@PUT
	@Path("/post/{postId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public Post putPost(Post post) {
		return dashBoardService.savePost(post);
	}
	
	@DELETE
	@Path("/post/{postId}")
	public void deletePost(@PathParam("postId") int postId) {
		dashBoardService.deletePost(postId);
	}
	
	@POST
	@Path("/post-comment/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public PostComment postPostComment(@Valid PostComment postComment) {
		return dashBoardService.savePostComment(postComment);
	}
	
	@PUT
	@Path("/post-comment/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler(ExceptionMapperImpl.class) 
	public PostComment putPostComment(@Valid PostComment postComment) {
		return dashBoardService.savePostComment(postComment);
	}
	
	@DELETE
	@Path("/post-comment/{postCommentId}")
	@ExceptionHandler(ExceptionMapperImpl.class) // generic exception handling
	@Produces(MediaType.APPLICATION_JSON) // must have this because generic exception handling creates json response message
	public void deletePostComment(@PathParam("postCommentId") int postCommentId) {
		dashBoardService.deletePostComment(postCommentId);
	}
	
}
