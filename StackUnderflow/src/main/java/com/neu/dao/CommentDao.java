package com.neu.dao;

import java.util.List;

import com.neu.model.Comment;
import com.neu.model.Like;

/**
 * @author Keshav
 *
 */
public interface CommentDao {
	public Comment addComment(Comment comment);

	public List<Comment> getCommentsofPost(int Postid);
	
	 public Comment getComment(int commentId);
	
	public void deleteComment(int commentId);
}

