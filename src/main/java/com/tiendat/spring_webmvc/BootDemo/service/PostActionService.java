package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.Date;
import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.PostAction;

public interface PostActionService {

	List<PostAction> findAll();
	List<PostAction> findPostByPostId(int postId);
	List<PostAction> findPostByMemberId(String memberId);
	List<PostAction> findPostByAction(int action);
	List<PostAction> findPostByDate(Date date);
	
	PostAction addAction(PostAction action);
	
	
}
