package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.respository.EventRepository;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> findAllEvent() {	
		return this.eventRepository.findAll();
	}

	@Override
	public Event insertEvent(Event event) {
		return eventRepository.saveAndFlush(event);
	}

	@Override
	public List<Event> findEventByName(String name) {
		return eventRepository.findByNameContaining(name);
	}

}
