package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Event;

public interface EventService {

	List<Event> findAllEvent();
	List<Event> findEventByName(String name);
	
	Event insertEvent(Event event);
	
	
}
