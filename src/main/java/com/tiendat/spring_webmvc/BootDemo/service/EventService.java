package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;

public interface EventService {

	List<Event> findAllEvent();

	List<Event> findEventByName(String name);

	List<Event> findAllPublicEvent();
	
	List<Event> findOldEvent();
	
	List<Event> findCurrentEvent();
	
	List<Event> findUpcomingEvent();

	Event findEventById(int eventId);

	Event insertEvent(Event event);

	Event update(Event event);

	int delete(int eventId);
	
}
