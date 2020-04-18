package com.neu.dao;
/**
 * @author Keshav
 *
 */
import com.neu.model.User;


import java.util.List;

/**
 *
 * @author kesha
 */
public interface UserDao {
 
 public User addUser(User user);
 public User UpdateUser(User user);
 public List<User> listUsers();
 
 public User getUser(String username);
 
 public void deleteUser(String name);
 
 public User findByuname(String uname);
 
 public User findByemail(String email);
}


