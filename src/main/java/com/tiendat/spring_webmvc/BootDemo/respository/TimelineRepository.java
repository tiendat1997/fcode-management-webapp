package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Timeline;

public interface TimelineRepository extends JpaRepository<Timeline, Long>{

	Timeline findById(int id);
	
	List<Timeline> findByEventId(int eventId);
	
	Timeline save(Timeline timeline);
	
	@Transactional
	Long deleteById(int id);
	
	
}
