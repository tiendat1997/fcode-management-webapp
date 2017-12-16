package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.Date;
import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.PictureAction;

public interface PictureActionService {

	List<PictureAction> findAll();
	List<PictureAction> findActionByMemberId(String memberId);
	List<PictureAction> findActionByAction(int action);
	List<PictureAction> findActionByPictureId(int pictureId);
	List<PictureAction> findActionByDate(Date date);
	
	PictureAction addAcion(PictureAction action);
	
}
