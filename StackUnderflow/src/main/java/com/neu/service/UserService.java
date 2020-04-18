package com.neu.service;
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
public interface UserService {
    

/**
 * @author Dinesh Rajput
 *
 */

 
 public User addUser(User user);

 public List<User> listUsers();
 
 public User getUser(String uname);
 
 public User getUserfromemail(String email);
 
 public void deleteUser(String name);
 public void UpdateUser(User user);
}


