package com.tiendat.spring_webmvc.BootDemo.service;

import java.sql.Date;
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
		return eventRepository.save(event);
	}

	@Override
	public List<Event> findEventByName(String name) {
		return eventRepository.findByNameContaining(name);
	}

	@Override
	public Event update(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public int delete(int eventId) {
		return eventRepository.deleteByEventId(eventId);
	}

	@Override
	public List<Event> findAllPublicEvent() {
		return this.eventRepository.findByNotPublicIsFalse();
	}

	@Override
	public List<Event> findOldEvent() {
		return this.eventRepository.findByDateEndBefore(new Date(System.currentTimeMillis()));
	}

	@Override
	public List<Event> findCurrentEvent() {
		return this.eventRepository.findByDateStartBeforeAndDateEndAfter(new Date(System.currentTimeMillis()));
	}

	@Override
	public List<Event> findUpcommingEvent() {
		return this.eventRepository.findByDateStartAfter(new Date(System.currentTimeMillis()));
	}

	@Override
	public Event findEventById(int eventId) {
		return this.eventRepository.findByEventId(eventId);
	}

}
