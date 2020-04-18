package com.neu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author Keshav
 *
 */
@Entity
@Table(name = "post")
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	private String text;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "id.post")
//	 @JoinColumns({
//	        @JoinColumn(
//	            name = "postId",
//	            referencedColumnName = "postId"),
//	        @JoinColumn(
//	            name = "username",
//	            referencedColumnName = "username")
//	    })
	private Set<Like> likes = new HashSet<Like>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
	private Set<Comment> comments = new HashSet<Comment>();
	
	public Set<Like> getLikes() {
		return likes;
	}

	public void setLikes(Set<Like> likes) {
		this.likes = likes;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

//	public Set<User> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(Set<User> likes) {
//		this.likes = likes;
//	}

}
