package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Timeline;

public interface TimelineRepository extends JpaRepository<Timeline, Long>{

	List<Timeline> findByEventId(int eventId);
	
	
}
