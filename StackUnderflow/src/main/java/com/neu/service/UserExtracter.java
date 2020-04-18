package com.neu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neu.stackunderflow.JwtTokenUtil;
import com.neu.model.User;
import com.neu.service.UserService;

/**
 * @author Keshav
 *
 */
@Service
public class UserExtracter {
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	UserService userService;
 public User getUserFromtoken(String requestTokenHeader) {
	    String email = null;
		String jwtToken = null;
		if (requestTokenHeader != null) {
		
			try {
				
				email = jwtTokenUtil.getUsernameFromToken(requestTokenHeader);
				
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println("JWT Token has expired");
			}
		} else {
			//logger.warn("JWT Token does not begin with Bearer String");
		}

//userService.addUser(user);
User user=userService.getUserfromemail(email);
return user;
 }
}

