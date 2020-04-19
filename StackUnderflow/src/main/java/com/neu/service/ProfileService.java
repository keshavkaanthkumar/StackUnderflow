package com.neu.service;

import java.util.List;

import com.neu.model.ProfileRequest;
import com.neu.model.Education;
import com.neu.model.Experience;
import com.neu.model.Profile;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
public interface ProfileService {
	public Profile addProfile(Profile profile, User user);

	 public List<Profile> listProfiles();
	 
	 public Profile getProfile(User user);
	 
	 public void deleteProfile(String username);
	 public Profile updateProfile(Profile profile,User user);

	void getProfileFromEmail(String email);

	void addExperience(Experience experience, Profile profile);

	public void deleteExperience(int expid);

	void addEducation(Education education, Profile profile);

	void deleteEducation(int eduid);
}

