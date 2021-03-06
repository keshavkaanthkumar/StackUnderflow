package com.neu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
@Repository("postDaoImpl")
public interface LikeDao {
	public Like addLike(Like like);

	public List<Like> getLikesofPost(int postId);
	
	 public int getLike(Post post,User user );
	
	public void deleteLike(User user, Post post);

	void deleteLikebyPostId(int postId);

	void deleteLikebyuser(String uname);

	
}

