package com.neu.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neu.model.Error;
import com.neu.model.ErrorResponse;
import com.neu.model.Post;
import com.neu.model.Profile;
import com.neu.model.User;
import com.neu.service.PostService;
import com.neu.service.ProfileService;
import com.neu.service.UserExtracter;
import com.neu.service.UserService;

/**
 * @author Keshav
 *
 */
@RestController
@RequestMapping("/")
public class PostsController {
	 @Autowired
	    UserExtracter userExtractor;
		@Autowired
	   PostService postService;
		@Autowired
		ProfileService profileService;
		   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST,
		      value="/post/me")
		   public ResponseEntity<?> save(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader,@RequestBody Post post) {
		         User user=userExtractor.getUserFromtoken(requestTokenHeader);
				Post postres=postService.addPost(post, user);
		      return new ResponseEntity<Post>(postres,HttpStatus.OK);
		   }
		   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
		      value="/post/me")
		   public ResponseEntity<?> update(@RequestHeader(value="Authorization",required = true) String requestTokenHeader,@RequestBody Post post) {
			   User user=userExtractor.getUserFromtoken(requestTokenHeader);
			   postService.updatePost(post);
		      return new ResponseEntity<String>("Post has been updated",HttpStatus.OK);
		   }
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET,
		      value="/post/{id}")
		   public ResponseEntity<?> getbyId(@PathVariable(value = "id") int postId) {
			  // User user=userExtractor.getUserFromtoken(requestTokenHeader);
			   Post post=postService.getPost(postId);
		
		      return new ResponseEntity<Post>(post,HttpStatus.OK);
		   }
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET,
		      value="/posts")
		   public ResponseEntity<?> get(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
			  // User user=userExtractor.getUserFromtoken(requestTokenHeader);
			   List<Post> posts=postService.listPosts();
		
		      return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
		   }
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.DELETE,
		      value="/post/{postId}")
		   public ResponseEntity<?> removeComment(@PathVariable(value="postId")int postId,@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
		        
		         User user;
				   try {
				   user=userExtractor.getUserFromtoken(requestTokenHeader);
				  }
				  catch(Exception ex) {
					  Error error=new Error();
				   	   //error.setValue(ex.getMessage());
				   	   error.setMsg("Invalid/Expired token");
				   	   List<Error> errorlist=new ArrayList<>();
				   	   errorlist.add(error);
				          ErrorResponse errors = new ErrorResponse(errorlist);
				   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
				  }
				postService.deletePost(postId);
				Profile profile=profileService.getProfile(user);
		      return new ResponseEntity<Profile>(profile,HttpStatus.OK);
		   }
		   
}

