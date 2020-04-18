package com.neu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.Profile;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
@Repository
public class LikeDaoImpl implements LikeDao {
	 @Autowired
	 private SessionFactory sessionFactory;
	@Override
	public Like addLike(Like like) {
		sessionFactory.getCurrentSession().persist(like);
		return like;
	}

	@Override
	public void deleteLike(User user, Post post) {
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Like WHERE postId = :postId AND username = :username");
		  q.setInteger("postId", post.getPostId());
		  q.setString("username", user.getName());
		  q.executeUpdate();
		
	}

	@Override
	public List<Like> getLikesofPost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Like getLike(int likeId) {
		// TODO Auto-generated method stub
		return null;
	}

}

