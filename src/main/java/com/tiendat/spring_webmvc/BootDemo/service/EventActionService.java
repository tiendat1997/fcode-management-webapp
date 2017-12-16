package com.tiendat.spring_webmvc.BootDemo.service;

import java.sql.Timestamp;
import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.EventAction;

public interface EventActionService {

	List<EventAction> findAll();
	List<EventAction> findActionByEventId(int eventId);
	List<EventAction> findActionByMemberId(String memberId);
	List<EventAction> findActionByAction(int action);
	List<EventAction> findActionByDate(Timestamp date);
	
	EventAction addAction(EventAction eventAction);
	
	
	
	
}
