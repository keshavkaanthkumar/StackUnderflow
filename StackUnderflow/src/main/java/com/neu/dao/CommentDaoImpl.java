package com.neu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.model.Comment;
import com.neu.model.Like;

/**
 * @author Keshav
 *
 */
@Repository
public class CommentDaoImpl implements CommentDao {
	 @Autowired
	 private SessionFactory sessionFactory;
	@Override
	public Comment addComment(Comment comment) {
		sessionFactory.getCurrentSession().persist(comment);
		return comment;
	}

	@Override
	public List<Comment> getCommentsofPost(int Postid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getComment(int commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(int commentId) {
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Comment WHERE commentId = :commentId");
		  q.setInteger("commentId", commentId);
		  q.executeUpdate();
		
	}

}

