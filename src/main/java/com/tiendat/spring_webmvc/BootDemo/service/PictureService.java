package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Picture;


public interface PictureService {

	List<Picture> findAllPicture();
	List<Picture> findPictureByEventId(int eventId);
	
	Picture uploadPicture(Picture newPicture);
	int deletePicture(int pictureId);
	
}
