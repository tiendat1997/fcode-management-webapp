package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAll();
	Post findByPostId(int postId);
	List<Post> findByEventId(int eventId);
	List<Post> findByNameContaining(String name);
	List<Post> findByNotPublicIsFalse();
	
	Post save(Post post);

	int deleteByPostId(int post);
	
	
}
