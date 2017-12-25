package com.tiendat.spring_webmvc.BootDemo.respository;

import java.sql.Date;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiendat.spring_webmvc.BootDemo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	List<Event> findAll();
	List<Event> findByNameContaining(String name);
	List<Event> findByNotPublicIsFalse();
	//find old event
	List<Event> findByDateEndBefore(Date date);
	//find up comming event
	List<Event> findByDateStartAfterOrderByDateStartAsc(Date date);
	//find current event
	@Query ("select e from event e where dateStart <= ?1 and dateEnd >= ?1")
	List<Event> findByDateStartBeforeAndDateEndAfter(Date date);
	
	
	Event findByEventId(int eventId);
	
	
	Event save(Event event);
	int deleteByEventId(int eventId);
	
	
}
