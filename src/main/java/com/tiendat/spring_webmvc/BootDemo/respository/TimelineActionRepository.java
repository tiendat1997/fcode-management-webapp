package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.TimelineAction;

public interface TimelineActionRepository extends JpaRepository<TimelineAction, Long>{

	List<TimelineAction> findAll();
	
	TimelineAction save(TimelineAction action);
	
}
