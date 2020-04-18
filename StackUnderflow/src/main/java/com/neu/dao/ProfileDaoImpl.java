package com.neu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.model.Post;
import com.neu.model.Profile;
import com.neu.model.User;



/**
 * @author Keshav
 *
 */
@Repository("profileDao")
public class ProfileDaoImpl implements ProfileDao{
	 @Autowired
	 private SessionFactory sessionFactory;

	@Override
	public Profile addProfile(Profile profile) {
		// TODO Auto-generated method stub
		
		 sessionFactory.getCurrentSession().save(profile);
		   return profile;
	}
	@Override
	public Profile updateProfile(Profile profile) {
		// TODO Auto-generated method stub
		
		 sessionFactory.getCurrentSession().update(profile);
		   return profile;
	}
	@Override
	public List<Profile> listProfiles() {
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Profile");
		
		  List<Profile> profiles=q.list();
		return profiles;
	}

	@Override
	public Profile getProfile(String username) {
		// TODO Auto-generated method stub
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Profile WHERE username = :uname");
		  q.setString("uname", username);
		  Profile profile=(Profile) q.uniqueResult();
		  return profile;
	}

	@Override
	public void deleteProfile(int profileId) {
		// TODO Auto-generated method stub
		
	}
	

}

