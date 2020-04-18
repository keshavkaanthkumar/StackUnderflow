package com.neu.service;
/**
 * @author Keshav
 *
 */
import com.neu.dao.UserDao;
import com.neu.exceptions.InvalidUserDetailsException;
import com.neu.model.Profile;
import com.neu.model.Skill;
import com.neu.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

/**
 *
 * @author kesha
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

 @Autowired
 private UserDao userDao;
 
 @Autowired
	private PasswordEncoder bcryptEncoder;

 public User addUser(User user) throws InvalidUserDetailsException {
	 User newUserObj=new User();
	 newUserObj.setEmail(user.getEmail());
	 newUserObj.setName(user.getName());
	 newUserObj.setPassword(bcryptEncoder.encode(user.getPassword()));
  userDao.addUser(newUserObj);
  return newUserObj;
 }
 
 public List<User> listUsers() {
  return userDao.listUsers();
 }

 public User getUser(String username) {
  return userDao.findByuname(username);
 }
 
 public void deleteUser(String name) {
  userDao.deleteUser(name);
 }



@Override
public User getUserfromemail(String email) {
	// TODO Auto-generated method stub
	return userDao.findByemail(email);
}

@Override
public void UpdateUser(User user) {
	// TODO Auto-generated method stub
	 User newUserObj=new User();
	 newUserObj.setEmail(user.getEmail());
	 newUserObj.setName(user.getName());
	 newUserObj.setPassword(bcryptEncoder.encode(user.getPassword()));
  userDao.UpdateUser(newUserObj);
  
	
}



}


