package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.PostAction;
import com.tiendat.spring_webmvc.BootDemo.respository.PostActionRepository;

@Service("postActionService")
@Transactional
public class PostActionServiceImpl implements PostActionService {

	@Autowired
	private PostActionRepository postActionRepository;
	
	@Override
	public List<PostAction> findAll() {
		return this.postActionRepository.findAll();
	}

	@Override
	public List<PostAction> findPostByPostId(int postId) {
		return this.postActionRepository.findByPostId(postId);
	}

	@Override
	public List<PostAction> findPostByMemberId(String memberId) {
		return this.postActionRepository.findByMemberId(memberId);
	}

	@Override
	public List<PostAction> findPostByAction(int action) {
		return this.postActionRepository.findByAction(action);
	}

	@Override
	public List<PostAction> findPostByDate(Date date) {
		return this.postActionRepository.findByDate(date);
	}

	@Override
	public PostAction addAction(PostAction action) {
		return this.postActionRepository.save(action);
	}

	
	
}
