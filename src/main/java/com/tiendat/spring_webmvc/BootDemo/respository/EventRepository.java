package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiendat.spring_webmvc.BootDemo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	List<Event> findAll();
	@Query("select e from event e where e.name like %?1%")
	List<Event> findByNameContaining(String name);
	List<Event> findByNotPublicIsFalse();
	
	Event save(Event event);
	int deleteByEventId(int eventId);
	
	
}
