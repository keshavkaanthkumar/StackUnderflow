package com.neu.model;

/**
 * @author Keshav
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "profile")
public class Profile {
	public Profile(){
		this.skills=new HashSet<Skill>();
	}
//	@GeneratedValue(generator = "User_Gen")
//	@SequenceGenerator(name = "User_Gen", sequenceName = "User_Seq", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "profileId")
	private int profileId;
	private String company;
	private String website;
	private String location;
	private String status;
	private String bio;
	private String githubusername;
	private String instagram;
	private String facebook;
	private String twitter;
	private String youtube;
	private String linkedin;
//	@OneToOne(fetch = FetchType.EAGER,cascade= CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "socialId", referencedColumnName = "socialId")
//	private Social social;
    
	public String getInstagram() {
		return instagram;
	}



	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}



	public String getFacebook() {
		return facebook;
	}



	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}



	public String getTwitter() {
		return twitter;
	}



	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}



	public String getYoutube() {
		return youtube;
	}



	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}



	public String getLinkedin() {
		return linkedin;
	}



	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinTable(name = "Profile_skills", joinColumns = {
			@JoinColumn(referencedColumnName = "profileId") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "name") })
	private Set<Skill> skills;
	@OneToOne(fetch = FetchType.EAGER,cascade= CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "profile")
	private Set<Experience> experience = new HashSet<Experience>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "profile")
	private Set<Education> education = new HashSet<Education>();
//	
//	public Social getSocial() {
//		return social;
//	}
//
//	public void setSocial(Social social) {
//		this.social = social;
//	}

//	public Set<Experience> getExperience() {
//		return experience;
//	}

	public int getProfileId() {
		return profileId;
	}



	public Set<Experience> getExperience() {
		return experience;
	}



	public void setExperience(Set<Experience> experience) {
		this.experience = experience;
	}



	public Set<Education> getEducation() {
		return education;
	}

	public void setEducation(Set<Education> education) {
		this.education = education;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}



	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getGithubusername() {
		return githubusername;
	}

	public void setGithubusername(String githubusername) {
		this.githubusername = githubusername;
	}

}
