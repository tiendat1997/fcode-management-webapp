package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Post;

public interface PostService {

	List<Post> findAllPost();
	List<Post> findPostByName(String name);
	List<Post> findPostByEventId(int eventId);
	List<Post> findPublicPost();
	
	Post findPostByPostId(int postId);
	Post insertPost(Post post);
	Post updatePost(Post post);
	
	
	
}
