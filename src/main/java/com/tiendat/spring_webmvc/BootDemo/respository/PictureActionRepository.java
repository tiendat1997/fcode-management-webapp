package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.PictureAction;

public interface PictureActionRepository extends JpaRepository<PictureAction, Long>{
	
	List<PictureAction> findAll();
	List<PictureAction> findByMemberId(String memberId);
	List<PictureAction> findByAction(int action);
	List<PictureAction> findByPictureId(int pictureId);
	List<PictureAction> findByDate(Date date);
	
	PictureAction save(PictureAction pictureAction);
	
}
