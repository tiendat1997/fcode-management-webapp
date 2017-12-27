package com.tiendat.spring_webmvc.BootDemo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventAction;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.respository.EventActionRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.EventRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineRepository;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private TimelineRepository timelineRepository;
	
	@Autowired
	private EventActionRepository eventActionRepository;
	
	private EventInformation getAllInfomation(Event event) {
		SimpleDateFormat format = new SimpleDateFormat("M/d/y");
		return new EventInformation(
				event.getEventId(), 
				event.getName(), 
				format.format(event.getDateStart()),
				format.format(event.getDateEnd()), 
				event.getDescription(), 
				event.isNotPublic(), 
				timelineRepository.findByEventId(event.getEventId()));
	}
	
	private List<EventInformation> getAllListInfomation(List<Event> events){
		List<EventInformation> listEvents = null;
		for (Event event:events) {
			if (listEvents == null)
				listEvents = new ArrayList<>();
			listEvents.add(getAllInfomation(event));
		}
		return listEvents;
	}
	
	@Override
	public List<EventInformation> findAllEvent() {	
		List<Event> events =  this.eventRepository.findAll();
		return this.getAllListInfomation(events);
	}

	
	@Override
	public List<EventInformation> findEventByName(String name) {
		List<Event> events =  eventRepository.findByNameContaining(name);
		return this.getAllListInfomation(events);
	}

	@Override
	public List<EventInformation> findAllPublicEvent() {
		List<Event> events = this.eventRepository.findByNotPublicIsFalse();
		return this.getAllListInfomation(events);
	}

	@Override
	public List<EventInformation> findOldEvent() {
		List<Event> events = this.eventRepository.findByDateEndBefore(new Date(System.currentTimeMillis()));
		return this.getAllListInfomation(events);
	}

	@Override
	public List<EventInformation> findCurrentEvent() {
		List<Event> events = this.eventRepository.findByDateStartBeforeAndDateEndAfter(new Date(System.currentTimeMillis()));
		return this.getAllListInfomation(events);
	}

	@Override
	public List<EventInformation> findUpcomingEvent() {
		List<Event> events = this.eventRepository.findByDateStartAfterOrderByDateStartAsc(new Date(System.currentTimeMillis()));
		return this.getAllListInfomation(events);
	}

	@Override
	public EventInformation findEventById(int eventId) {
		Event event = eventRepository.findByEventId(eventId);
		return this.getAllInfomation(event);
	}

	@Override
	public Event insertEvent(Event event, String username) {
		Event e = eventRepository.saveAndFlush(event);
		//add default timeline for event
		Timeline timeline = new Timeline(event.getName(),"",event.getEventId(),event.getDateStart(),event.getDateEnd());
		timelineRepository.save(timeline);
		EventAction action = new EventAction(username, e.getEventId(), 1, new java.util.Date());
		this.eventActionRepository.save(action);
		return e;
	}
	
	@Override
	public Event update(Event event, String username) {
		
		EventAction action = new EventAction(username, event.getEventId(), 3, new java.util.Date());
		this.eventActionRepository.save(action);
		return eventRepository.save(event);
	}


	@Override
	public int delete(int eventId) {
		return eventRepository.deleteByEventId(eventId); 
	}

}
