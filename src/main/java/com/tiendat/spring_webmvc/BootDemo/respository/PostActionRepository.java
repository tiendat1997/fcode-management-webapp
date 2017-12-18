package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.PostAction;

public interface PostActionRepository extends JpaRepository<PostAction, Long>{
	
	List<PostAction> findAll();
	List<PostAction> findByPostId(int postId);
	List<PostAction> findByMemberId(String memberId);
	List<PostAction> findByAction(int action);
	List<PostAction> findByDate(Date date);
	
	PostAction save(PostAction action);
	
	
}
