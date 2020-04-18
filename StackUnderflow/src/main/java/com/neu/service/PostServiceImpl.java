package com.neu.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.dao.PostDao;
import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
@Service("postService")
@Transactional
public class PostServiceImpl implements PostService{

	@Autowired
	PostDao postdao;
	@Override
	public Post addPost(Post post, User user) {
		// TODO Auto-generated method stub
		post.setUser(user);
	   return  postdao.addPost(post);
	}
	
	public Set<Like> getPostLikes(Post post){
		Set<Like> likes=post.getLikes();
		return likes;
	}

	@Override
	public List<Post> listPosts() {
		// TODO Auto-generated method stub
		List<Post> posts=postdao.listPosts();
		return posts;
	
	}

	@Override
	public List<Post> listPosts(User user) {
		// TODO Auto-generated method stub
		List<Post> posts=postdao.listPosts();
		return posts;
	}

	@Override
	public Post getPost(int postId) {
	return postdao.getPost(postId);
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}

