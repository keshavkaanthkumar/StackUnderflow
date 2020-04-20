package com.neu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.model.Post;
import com.neu.model.Profile;

/**
 * @author Keshav
 *
 */
@Repository("postDaoImpl")
public class PostDaoImpl implements PostDao{
	 @Autowired
	 private SessionFactory sessionFactory;
	 @Autowired
	 private CommentDao commentdao;
	 @Autowired
	 private LikeDao likedao;
	@Override
	public Post addPost(Post post) {
		
		 sessionFactory.getCurrentSession().persist(post);
		 return post;
	}

	@Override
	public List<Post> listPosts() {
		
		//List<Post> posts = sessionFactory.getCurrentSession().createCriteria(Post.class).list();
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Post");
		 // q.setInteger("postId", postId);
		  List<Post> posts=q.list();
		 
		return posts;
	}

	@Override
	public List<Post> listPosts(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPost(int postId) {
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Post WHERE postId = :postId");
		  q.setInteger("postId", postId);
		  Post post=(Post) q.uniqueResult();
		  return post;
	}
	@Override
	public List<Post> getPostbyuser(String uname) {
		Query q =sessionFactory.getCurrentSession().createQuery("FROM Post WHERE username = :uname");
		  q.setString("uname", uname);
		  List<Post> posts=q.list();
		  return posts;
	}

	@Override
	public void deletePost(int postId) {
		// TODO Auto-generated method stub
		commentdao.deleteCommentbypostId(postId);
		likedao.deleteLikebyPostId(postId);
		Query q =sessionFactory.getCurrentSession().createQuery("Delete FROM Post WHERE postId = :postId");
		  q.setInteger("postId", postId);
		  q.executeUpdate();
	}
	@Override
	public void deletePostbyuser(String uname) {
		
		List<Post> posts=getPostbyuser(uname);
		for(Post post:posts) {
			deletePost(post.getPostId());
		}
		
	}
	

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}

