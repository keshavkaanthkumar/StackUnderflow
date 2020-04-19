package com.neu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.Profile;
import com.neu.model.User;
import com.neu.service.LikeService;
import com.neu.service.PostService;
import com.neu.service.ProfileService;
import com.neu.service.UserExtracter;

/**
 * @author Keshav
 *
 */
@RestController
@RequestMapping("/")
public class LikesController {
	 @Autowired
	    UserExtracter userExtractor;
		@Autowired
		LikeService likeService;
		@Autowired
		PostService postService;
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
		      value="/like/{id}")
		   public ResponseEntity<?> like(@PathVariable(value = "id") int postId,@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
		         User user=userExtractor.getUserFromtoken(requestTokenHeader);
				Post post=postService.getPost(postId);
				try {
					likeService.addLike(user, post);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Error error=new Error();
				   	   //error.setValue(ex.getMessage());
				   	   error.setMsg(e.getMessage());
				   	   List<Error> errorlist=new ArrayList<>();
				   	   errorlist.add(error);
				          ErrorResponse errors = new ErrorResponse(errorlist);
				   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
				}
				Post postres=postService.getPost(postId);
		      return new ResponseEntity<Set<Like>>(postService.getPostLikes(postres),HttpStatus.OK);
		   }
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
		      value="/unlike/{id}")
		   public ResponseEntity<?> removelike(@PathVariable(value = "id") int postId,@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
		        User user=userExtractor.getUserFromtoken(requestTokenHeader);
		        Post post=postService.getPost(postId);
				likeService.removeLike(user,post);
				Post postres=postService.getPost(postId);
		      return new ResponseEntity<Set<Like>>(postService.getPostLikes(postres),HttpStatus.OK);
		   }
}

