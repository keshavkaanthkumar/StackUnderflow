package com.neu.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.model.Profile;
import com.neu.model.ProfileRequest;
import com.neu.model.Skill;
import com.neu.model.Social;

/**
 * @author Keshav
 *
 */
@Service
public class ProfileRequestConvertor {
	
   public Profile ConvertRequestToProfile(ProfileRequest profileRequest) {
	  Profile profile=new Profile();
		 profile.setBio(profileRequest.getBio());
		 profile.setCompany(profileRequest.getCompany());
		 profile.setGithubusername(profileRequest.getGithubusername());
		 profile.setStatus(profileRequest.getStatus());	
		 Social social=new Social();
//		 social.setFacebook(profileRequest.getFacebook());
//		 social.setInstagram(profileRequest.getInstagram());
//		 social.setLinkedin(profileRequest.getLinkedin());
//		 social.setTwitter(profileRequest.getTwitter());
//		 social.setYoutube(profileRequest.getYoutube());
		// profile.setSocial(social);
//		 List<String> skills = Arrays.asList(profileRequest.getSkills().split(","));
//		 Set<Skill> skillset = new HashSet<Skill>();
//		 for(String skill:skills) {
//			 skillset.add(new Skill(skill,profile));
//			 
//		 }
//		// skillset.addAll(skills); 
//		 profile.setSkills(skillset);
		return profile;
   }
   public Profile ConvertRequestToProfileUpdate(ProfileRequest profileRequest,Profile profile) {
		 //  Profile profile=new Profile();
			 profile.setBio(profileRequest.getBio());
			 profile.setCompany(profileRequest.getCompany());
			 profile.setGithubusername(profileRequest.getGithubusername());
			 profile.setStatus(profileRequest.getStatus());	
			// Social social=new Social();
//			 social.setFacebook(profileRequest.getFacebook());
//			 social.setInstagram(profileRequest.getInstagram());
//			 social.setLinkedin(profileRequest.getLinkedin());
//			 social.setTwitter(profileRequest.getTwitter());
//			 social.setYoutube(profileRequest.getYoutube());
			// profile.setSocial(social);
//			 List<String> skills = Arrays.asList(profileRequest.getSkills().split(","));
//			 Set<Skill> skillset = new HashSet<Skill>();
//			 for(String skill:skills) {
//				 skillset.add(new Skill(skill,profile));
//				 
//			 }
//			// skillset.addAll(skills); 
//			 profile.setSkills(skillset);
			return profile;
	   }
}

