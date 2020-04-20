package com.neu.dao;
/**
 * @author Keshav
 *
 */

import java.util.List;

import com.neu.model.User;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


/**
 *
 * @author kesha
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

 @Autowired
 private SessionFactory sessionFactory;
 @Autowired
 ProfileDao profileDao;
 public User addUser(User user) {
   sessionFactory.getCurrentSession().save(user);
   return user;
 }
 public User UpdateUser(User user) {
	   sessionFactory.getCurrentSession().update(user);
	   return user;
	 }

 @SuppressWarnings("unchecked")
 public List<User> listUsers() {
	 System.out.println("Kk");
  return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
 }

 public User getUser(String name) {
  return (User) sessionFactory.getCurrentSession().get(User.class, name);
 }

 public void deleteUser(String uname) {
	profileDao.deleteProfile(uname);
     Query q =sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE username = :username");
  q.setString("username", uname);
  int i=q.executeUpdate();
  if(i==0) {
	  String msg="User not found";
	  UsernameNotFoundException exception=new UsernameNotFoundException(msg);
	  throw exception;
  }
 }

public User findByuname(String uname) {
	// TODO Auto-generated method stub
	Query q =sessionFactory.getCurrentSession().createQuery("FROM User WHERE name = :uname");
	  q.setString("uname", uname);
	  User user=(User) q.uniqueResult();
	return user;
}

@Override
public User findByemail(String email) {
	// TODO Auto-generated method stub
	Query q =sessionFactory.getCurrentSession().createQuery("FROM User WHERE email = :email");
	  q.setString("email", email);
	  User user=(User) q.uniqueResult();
	return user;
	
}
}

