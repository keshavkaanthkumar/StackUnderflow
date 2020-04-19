package com.neu.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.dao.LikeDao;
import com.neu.model.Like;
import com.neu.model.LikeId;
import com.neu.model.Post;
import com.neu.model.User;

/**
 * @author Keshav
 *
 */
@Service
@Transactional
public class LikeServiceImpl implements LikeService{

	@Autowired
	LikeDao likedao;
	@Override
	public Like addLike(User user, Post post) throws Exception {
		if(likedao.getLike(post, user)>0) {
			throw new Exception("Post already liked");
		}
	    Like like=new Like();
	    //LikeId likeid=new LikeId(post,user);
	    like.setPost(post);
	    like.setUser(user);
	    likedao.addLike(like);
		return like;
	}
@Override
	public void removeLike(User user,Post post) {
		
	likedao.deleteLike(user, post);
		//likedao.deleteLike(likeid);
		
	}

}

