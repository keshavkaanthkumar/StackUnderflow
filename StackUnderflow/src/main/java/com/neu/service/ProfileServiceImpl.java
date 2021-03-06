package com.neu.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.dao.ProfileDao;
import com.neu.dao.UserDao;
import com.neu.model.Education;
import com.neu.model.Experience;
import com.neu.model.Profile;
import com.neu.model.Skill;
import com.neu.model.Social;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
@Service("profileService")
@Transactional
public class ProfileServiceImpl implements ProfileService{
	 @Autowired
	 private ProfileDao profileDao;
	 
	@Override
	public Profile addProfile(Profile profile,User user) {
		
	      profile.setUser(user);
	      return profileDao.addProfile(profile);
      
		
	}
	@Override
	public void addExperience(Experience experience, Profile profile) {
		experience.setProfile(profile);
		profileDao.addExperience(experience);
		 
	}
	@Override
	public void addEducation(Education education, Profile profile) {
		education.setProfile(profile);
		profileDao.addEducation(education);
		 
	}
	@Override
	public Profile updateProfile(Profile profile,User user) {
      profile.setUser(user);
     return  profileDao.updateProfile(profile);
      
		
	}
	@Override
	public Profile getProfile(User user) {
		return profileDao.getProfile(user.getName());
		
	}

	@Override
	public List<Profile> listProfiles() {
		
		return profileDao.listProfiles();
	}

	@Override
	public void deleteProfile(String username) {
		
		
	}
	@Override
	public void getProfileFromEmail(String email) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteExperience(int expid) {
		profileDao.deleteExperience(expid);
		
	}
	@Override
	public void deleteEducation(int eduid) {
		profileDao.deleteEducation(eduid);
		
	}

}

