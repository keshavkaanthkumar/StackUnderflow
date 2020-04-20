package com.neu.dao;

import java.util.List;

import com.neu.model.Education;
import com.neu.model.Experience;
import com.neu.model.Profile;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
public interface ProfileDao {
	public Profile addProfile(Profile profile);

	public List<Profile> listProfiles();

	public Profile getProfile(String username);

	public void deleteProfile(String uname);

	Profile updateProfile(Profile profile);

	public Experience addExperience(Experience profile);

	public void deleteExperience(int expid);

	Education addEducation(Education education);

	void deleteEducation(int eduid);

	// public User findByuname(String email);
}
