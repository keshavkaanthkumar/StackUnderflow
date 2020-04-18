package com.neu.service;

import com.neu.model.Comment;
import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
public interface CommentService {
	public Comment addComment(User user, Post post, Comment comment);
	public void removeComment(int commentid);
}

