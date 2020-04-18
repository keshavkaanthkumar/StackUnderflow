package com.neu.controller;

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

import com.neu.model.Comment;
import com.neu.model.Post;
import com.neu.model.User;
import com.neu.service.CommentService;
import com.neu.service.LikeService;
import com.neu.service.PostService;
import com.neu.service.UserExtracter;

/**
 * @author Keshav
 *
 */
@RestController
@RequestMapping("/")
public class CommentsController {
	 @Autowired
	    UserExtracter userExtractor;
		@Autowired
		CommentService commentService;
		@Autowired
		PostService postService;
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST,
		      value="/comment/{id}")
		   public ResponseEntity<?> comment(@PathVariable(value="id") int postId,@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader,@RequestBody Comment comment) {
		         User user=userExtractor.getUserFromtoken(requestTokenHeader);
				Post post=postService.getPost(postId);
				
				commentService.addComment(user, post, comment);
				Post postres=postService.getPost(postId);

		      return new ResponseEntity<Post>(postres,HttpStatus.OK);
		   }
		   @RequestMapping(
		            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.DELETE,
		      value="/comment")
		   public ResponseEntity<?> removeComment(@RequestParam int commentId,@RequestHeader(value="Authorization",required = true) String requestTokenHeader) {
		         User user=userExtractor.getUserFromtoken(requestTokenHeader);
				
				commentService.removeComment(commentId);
		      return new ResponseEntity<String>("Comment has been removed",HttpStatus.OK);
		   }
}

