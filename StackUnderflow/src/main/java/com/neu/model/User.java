package com.neu.model;
/**
 * @author Keshav
 *
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author kesha
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value= {"profile"})
public class User {

	@Id
	@Column(name = "username", unique = true)
	@Size(max = 20, min = 3, message = "{user.name.invalid}")
	private String name;

	@Column(name = "email", unique = true)
	// @Email(message = "{user.email.invalid}")
	private String email;

	@NotNull(message = "PASSWORD cannot be null")
	@Column(name = "password")
	private String password;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")	
    private Profile profile;
    
	// Getter and Setter methods
	// ...

	public String getName() {
		return name;
	}



	

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}
