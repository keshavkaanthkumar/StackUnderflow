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
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Like WHERE postId = :postId AND username = :username");
		
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
	public void deleteLikebyPostId(int postId) {
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Like WHERE postId = :postId");
		  q.setInteger("postId", postId);
	
		  q.executeUpdate();
		
	}
	@Override
	public void deleteLikebyuser(String uname) {
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Like WHERE username = :uname");
		  q.setString("uname", uname);
	
		  q.executeUpdate();
		
	}
	

	@Override
	public List<Like> getLikesofPost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLike(Post post, User user) {
		// TODO Auto-generated method stub
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Like WHERE postId = :postId AND username = :username");
		  q.setInteger("postId", post.getPostId());
		  q.setString("username", user.getName());
		  int i=q.list().size();
		return i;
	}

}

