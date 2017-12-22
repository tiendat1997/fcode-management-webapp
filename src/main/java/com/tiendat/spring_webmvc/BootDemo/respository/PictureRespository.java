package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Picture;

public interface PictureRespository extends JpaRepository<Picture, Long>{

	List<Picture> findAll();
	List<Picture> findByEventId(int eventId);
	List<Picture> findByNotPublicIsFalse();
	
	Picture save(Picture newPicture);	
	int deleteByPictureId(int pictureId);
	
}
