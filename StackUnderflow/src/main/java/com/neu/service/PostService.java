package com.neu.service;

import java.util.List;
import java.util.Set;

import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
public interface PostService {
	public Post addPost(Post post, User user);

	public List<Post> listPosts();

	public List<Post> listPosts(User user);

	public Post getPost(int postId);

	public void deletePost(int postId);

	public Post updatePost(Post post);
	public Set<Like> getPostLikes(Post post);

	}

