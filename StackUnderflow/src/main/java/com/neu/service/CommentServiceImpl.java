package com.neu.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.dao.CommentDao;
import com.neu.model.Comment;
import com.neu.model.Post;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;
	@Override
	public Comment addComment(User user, Post post, Comment comment) {
		comment.setUser(user);
		comment.setPost(post);
		return commentDao.addComment(comment);
	}

	@Override
	public void removeComment(int commentid) {
	
		commentDao.deleteComment(commentid);
		
	}

}

