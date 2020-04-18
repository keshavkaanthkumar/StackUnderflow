package com.neu.model;
/**
 * @author Keshav
 *
 */

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Embeddable

public class LikeId  implements Serializable{
	
//	@ManyToOne
//	@JoinColumn(name = "postId")
//	private Post post;
//	@ManyToOne
//	@JoinColumn(name = "username")
//	private User user;
//	public Post getPost() {
//		return post;
//	}
//	public void setPost(Post post) {
//		this.post = post;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public LikeId(Post post, User user){
//		this.post=post;
//		this.user=user;
//	}
//	public LikeId() {
//		
//	}
//	 @Override
//	    public boolean equals(Object o) {
//	        if (this == o) return true;
//	        if (!(o instanceof LikeId)) return false;
//	        LikeId that = (LikeId) o;
//	        return Objects.equals(getPost(), that.getPost()) &&
//	                Objects.equals(getUser(), that.getUser());
//	    }
//
//	    @Override
//	    public int hashCode() {
//	        return Objects.hash(getPost(), getUser());
//	    }
	
}

