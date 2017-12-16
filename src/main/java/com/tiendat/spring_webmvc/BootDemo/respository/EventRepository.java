package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	List<Event> findAll();
	List<Event> findByNameContaining(String name);
	
	Event save(Event event);
	int deleteByEventId(int eventId);
	
	
}
