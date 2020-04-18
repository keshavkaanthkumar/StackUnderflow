package com.neu.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.neu.model.UserDTO;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
public interface UserDetailService extends UserDetailsService{
	public User save(UserDTO user);
}

