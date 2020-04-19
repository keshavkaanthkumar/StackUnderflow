package com.neu.controller;
/**
 * @author Keshav
 *
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.neu.exceptions.InvalidUserDetailsException;
import com.neu.model.ErrorResponse;
import com.neu.model.JwtResponse;
import com.neu.model.Error;
import com.neu.model.User;
import com.neu.service.UserDetailService;
import com.neu.service.UserExtracter;
import com.neu.service.UserService;
import com.neu.service.UserServiceImpl;
import com.neu.stackunderflow.JwtTokenUtil;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author kesha
 */


@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserExtracter userExtractor;
   @Autowired
   private UserService userService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired(required=true)
	@Qualifier("jwtservice")
	private UserDetailService userDetailsService;
 
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
    		produces=MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.POST,
      value="/user")
   public ResponseEntity<?> save(@RequestBody User user) {
      try {
      userService.addUser(user);
      }
      catch(Exception ex) {
    	   Error error=new Error();
    	   error.setValue(ex.getMessage());
    	   error.setMsg("Invalid user details");
    	   List<Error> errorlist=new ArrayList<>();
    	   errorlist.add(error);
           ErrorResponse errors = new ErrorResponse(errorlist);
    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
      }
      final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());

		final String token = jwtTokenUtil.generateToken(userDetails);

      return new ResponseEntity<JwtResponse>(new JwtResponse(token,user),HttpStatus.OK);
   }
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
      value="/user")
   public ResponseEntity<?> update(@RequestBody User user) {
      
    	 try {
    	      userService.UpdateUser(user);
    	      }
    	      catch(Exception ex) {
    	    	   Error error=new Error();
    	    	   error.setValue(ex.getMessage());
    	    	   error.setMsg("User not present");
    	    	   List<Error> errorlist=new ArrayList<>();
    	    	   errorlist.add(error);
    	           ErrorResponse errors = new ErrorResponse(errorlist);
    	    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
    	      }
      return new ResponseEntity<String>("User has been updated",HttpStatus.OK);
   }

   @RequestMapping(
           produces = MediaType.APPLICATION_JSON_VALUE,method= RequestMethod.GET,
      value="/user")
   public ResponseEntity<?> get(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
	   User user;
	   try {
	   user=userExtractor.getUserFromtoken(requestTokenHeader);
	   }
	   catch(Exception ex) {
		   Error error=new Error();
    	   error.setValue(ex.getMessage());
    	   error.setMsg("Invalid token");
    	   List<Error> errorlist=new ArrayList<>();
    	   errorlist.add(error);
           ErrorResponse errors = new ErrorResponse(errorlist);
    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
	   }
	  // User user = userService.getUser(id);
      return new ResponseEntity<User>(user,HttpStatus.OK);
   }

//   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
//           produces = MediaType.APPLICATION_JSON_VALUE,method= RequestMethod.GET,
//      value="/user")
//   public ResponseEntity<?> list() {
//	   List<User> users;
//      try {
//    	  users= userService.listUsers();
//	      }
//	      catch(Exception ex) {
//	    	   Error error=new Error();
//	    	   error.setValue(ex.getMessage());
//	    	   error.setMsg("Internal server error");
//	    	   List<Error> errorlist=new ArrayList<>();
//	    	   errorlist.add(error);
//	           ErrorResponse errors = new ErrorResponse(errorlist);
//	    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
//	      }
//      return new ResponseEntity<List<User>>(users,HttpStatus.OK);
//   }
   @RequestMapping(
           produces = MediaType.APPLICATION_JSON_VALUE,method= RequestMethod.DELETE,
      value="/user")
   public ResponseEntity<?> delete(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
	   User user;
	   try {
	   user=userExtractor.getUserFromtoken(requestTokenHeader);
	   }
	   catch(Exception ex) {
		   Error error=new Error();
    	   error.setValue(ex.getMessage());
    	   error.setMsg("Invalid token");
    	   List<Error> errorlist=new ArrayList<>();
    	   errorlist.add(error);
           ErrorResponse errors = new ErrorResponse(errorlist);
    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
	   }
	   
      userService.deleteUser(user.getName());
      return new ResponseEntity<String>("User deleted",HttpStatus.OK);
   }


}


