package com.tiendat.spring_webmvc.BootDemo.respository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.EventAction;

public interface EventActionRepository extends JpaRepository<EventAction, Long>{
	
	List<EventAction> findAll();
	List<EventAction> findByEventId(int eventId);
	List<EventAction> findByMemberId(String memberId);
	List<EventAction> findByAction(int action);
	List<EventAction> findByDate(Timestamp date);
	
	EventAction save(EventAction action);
	
	
}
