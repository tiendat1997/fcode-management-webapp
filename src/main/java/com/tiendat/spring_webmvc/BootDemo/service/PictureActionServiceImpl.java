package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.PictureAction;
import com.tiendat.spring_webmvc.BootDemo.respository.PictureActionRepository;

@Service("pictureActionService")
@Transactional
public class PictureActionServiceImpl implements PictureActionService{

	@Autowired
	private PictureActionRepository pictureActionRepository;
	
	@Override
	public List<PictureAction> findAll() {
		return this.pictureActionRepository.findAll();
	}

	@Override
	public List<PictureAction> findActionByMemberId(String memberId) {
		return this.pictureActionRepository.findByMemberId(memberId);
	}

	@Override
	public List<PictureAction> findActionByAction(int action) {
		return this.pictureActionRepository.findByAction(action);
	}

	@Override
	public List<PictureAction> findActionByPictureId(int pictureId) {
		return this.pictureActionRepository.findByPictureId(pictureId);
	}

	@Override
	public List<PictureAction> findActionByDate(Date date) {
		return this.pictureActionRepository.findByDate(date);
	}

	@Override
	public PictureAction addAcion(PictureAction action) {
		return this.pictureActionRepository.save(action);
	}

}
