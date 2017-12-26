package com.tiendat.spring_webmvc.BootDemo.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineAction;
import com.tiendat.spring_webmvc.BootDemo.respository.EventRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineActionRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineRepository;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private TimelineRepository timelineRepository;
	
	
	
	@Override
	public List<Event> findAllEvent() {	
		return this.eventRepository.findAll();
	}

	@Override
	public Event insertEvent(Event event) {
		Event e = eventRepository.saveAndFlush(event);
		//add default timeline for event
		Timeline timeline = new Timeline(event.getName(),"",event.getEventId(),event.getDateStart());
		timelineRepository.save(timeline);
		return e;
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
	public List<Event> findUpcomingEvent() {
		return this.eventRepository.findByDateStartAfterOrderByDateStartAsc(new Date(System.currentTimeMillis()));
	}

	@Override
	public Event findEventById(int eventId) {
		return this.eventRepository.findByEventId(eventId);
	}

	

}
