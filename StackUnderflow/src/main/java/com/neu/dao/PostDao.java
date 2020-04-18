package com.neu.dao;

import java.util.List;

import com.neu.model.Post;
import com.neu.model.Profile;

/**
 * @author Keshav
 *
 */
public interface PostDao {
	public Post addPost(Post post);

	public List<Post> listPosts();

	public List<Post> listPosts(String username);

	public Post getPost(int postId);

	public void deletePost(int postId);

	public Post updatePost(Post post);
}
