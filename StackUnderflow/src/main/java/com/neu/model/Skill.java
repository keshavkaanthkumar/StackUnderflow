package com.neu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Keshav
 *
 */
@Entity
@Table(name = "skill")
@JsonIgnoreProperties(value= {"profile"})
public class Skill {
	public Skill(){
		this.profile=new HashSet<Profile>();
	}
	public Skill(String name,Profile profile) {
		this.name=name;
		this.profile.add(profile);
	}
	@Id
	@Column(name = "name")

	public String name;
	@ManyToMany(mappedBy = "skills")
	private Set<Profile> profile;

	public Set<Profile> getProfile() {
		return profile;
	}

	public void setProfile(Set<Profile> profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
