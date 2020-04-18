package com.neu.dao;

import java.util.List;

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
	 
	 public void deleteProfile(int profileId);

	Profile updateProfile(Profile profile);
	 
	// public User findByuname(String email);
}

