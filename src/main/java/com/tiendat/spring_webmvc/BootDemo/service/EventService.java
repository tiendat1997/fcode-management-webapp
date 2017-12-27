package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;

public interface EventService {

	List<EventInformation> findAllEvent();

	List<EventInformation> findEventByName(String name);

	List<EventInformation> findAllPublicEvent();
	
	List<EventInformation> findOldEvent();
	
	List<EventInformation> findCurrentEvent();
	
	List<EventInformation> findUpcomingEvent();

	EventInformation findEventById(int eventId);

	boolean insertEvent(Event event, String username);

	boolean update(Event event, String username);

	boolean delete(int eventId, String username);
	
}
