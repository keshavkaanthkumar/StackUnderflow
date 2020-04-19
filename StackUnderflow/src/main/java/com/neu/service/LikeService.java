package com.neu.service;
/**
 * @author Keshav
 *
 */

import com.neu.model.Like;
import com.neu.model.Post;
import com.neu.model.User;

public interface LikeService {
public Like addLike(User user, Post post) throws Exception;
public void removeLike(User user, Post post);
}

