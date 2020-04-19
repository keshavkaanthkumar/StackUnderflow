package com.neu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.neu.model.Error;
import com.neu.model.ErrorResponse;
import com.neu.model.JwtRequest;
import com.neu.model.JwtResponse;
import com.neu.model.UserDTO;
import com.neu.model.User;
import com.neu.service.JwtUserDetailsService;
import com.neu.service.UserDetailService;
import com.neu.service.UserService;
import com.neu.stackunderflow.JwtTokenUtil;



@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired(required=true)
	@Qualifier("jwtservice")
	private UserDetailService userDetailsService;
	  @Autowired
	   private UserService userService;
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
	           produces = MediaType.APPLICATION_JSON_VALUE,value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		User user = userService.getUserfromemail(authenticationRequest.getEmail());
		if(user==null) {
			  Error error=new Error();
	    	  // error.setValue(e.getMessage());
	    	   error.setMsg("Invalid email id");
	    	   List<Error> errorlist=new ArrayList<>();
	    	   errorlist.add(error);
	           ErrorResponse errors = new ErrorResponse(errorlist);
	    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.UNAUTHORIZED);
		}
		try {
		authenticate(user.getName(), authenticationRequest.getPassword());
		}
		 catch (DisabledException e) {
			  Error error=new Error();
	    	   error.setValue(e.getMessage());
	    	   error.setMsg("User Disabled");
	    	   List<Error> errorlist=new ArrayList<>();
	    	   errorlist.add(error);
	           ErrorResponse errors = new ErrorResponse(errorlist);
	    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.UNAUTHORIZED);
		} catch (BadCredentialsException e) {
			 Error error=new Error();
	    	   error.setValue(e.getMessage());
	    	   error.setMsg("Invalid email or password");
	    	   List<Error> errorlist=new ArrayList<>();
	    	   errorlist.add(error);
	           ErrorResponse errors = new ErrorResponse(errorlist);
	    	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.UNAUTHORIZED);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());

		final String token = jwtTokenUtil.generateToken(userDetails);

	
		return new ResponseEntity<JwtResponse>(new JwtResponse(token,user),HttpStatus.OK);
	}

//	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
//	           produces = MediaType.APPLICATION_JSON_VALUE,value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> save(@RequestBody UserDTO user) throws Exception {
//		return new ResponseEntity<User>(userDetailsService.save(user),HttpStatus.OK);
//	
//	}

	private void authenticate(String username, String password) throws Exception {
		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
	}
}
