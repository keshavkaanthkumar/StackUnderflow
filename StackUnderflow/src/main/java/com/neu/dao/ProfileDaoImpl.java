package com.neu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.model.Education;
import com.neu.model.Experience;
import com.neu.model.Post;
import com.neu.model.Profile;
import com.neu.model.Skill;
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
	public Experience addExperience(Experience experience) {
		// TODO Auto-generated method stub
		
		 sessionFactory.getCurrentSession().save(experience);
		   return experience;
	}
	@Override
	public Education addEducation(Education education) {
		// TODO Auto-generated method stub
		
		 sessionFactory.getCurrentSession().save(education);
		   return education;
	}
	@Override
	public Profile updateProfile(Profile profile) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().evict(profile);
		for(Skill skill:profile.getSkills()) {
			sessionFactory.getCurrentSession().saveOrUpdate(skill);
		}
		 sessionFactory.getCurrentSession().saveOrUpdate(profile);
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
	@Override
	public void deleteExperience(int expid) {
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Experience WHERE expid = :expid");
		  q.setInteger("expid", expid);
		  q.executeUpdate();
		  
		
	}
	@Override
	public void deleteEducation(int eduid) {
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Education WHERE eduid = :eduid");
		  q.setInteger("eduid", eduid);
		  q.executeUpdate();
		  
		
	}
	

}

