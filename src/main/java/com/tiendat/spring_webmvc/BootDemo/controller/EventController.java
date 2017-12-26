package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventAction;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.service.EventActionService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;

@RestController
@RequestMapping("admin/api/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventActionService eventActionService;
	
	

	@GetMapping(value = "/all")
	public List<Event> getAllEvent() {
		return this.eventService.findAllEvent();
	}
	
	@GetMapping(value = "/get/{name}")
	public List<Event> getByName(@PathVariable String name){
		return this.eventService.findEventByName(name);
	}
	
	@GetMapping(value = "/public")
	public List<Event> getPublicEvent(){
		return this.eventService.findAllPublicEvent();
	}

	@GetMapping(value = "/new", params = { "name", "dateStart", "dateEnd", "description", "username","notPublic"})
	public EventAction newEvent(@ModelAttribute Event event, @RequestParam("username") String username) {

		Event e =  this.eventService.insertEvent(event);
		EventAction action = new EventAction(username, e.getEventId(), 1, new Date());
		return this.eventActionService.addAction(action);
	}
	
	@GetMapping(value = "/update", params = { "eventId","name", "dateStart", "dateEnd", "description", "username","notPublic"})
	public EventAction updateEvent(@ModelAttribute Event event, @RequestParam("username") String username) {

		Event e =  this.eventService.update(event);
		EventAction action = new EventAction(username, e.getEventId(), 3, new Date());
		return this.eventActionService.addAction(action);
	}
	
	@GetMapping(value = "/get/id/{eventId}")
	public Event getEventById(@PathVariable("eventId") int eventId) {
		return this.eventService.findEventById(eventId);
	}
	
	@GetMapping(value = "/get/old")
	public List<Event> getOldEvent(){
		return this.eventService.findOldEvent();
	}
	
	@GetMapping(value = "/get/current")
	public List<Event> getCurrentEvent(){
		return this.eventService.findCurrentEvent();
	}
	
	@GetMapping(value = "/get/upcoming")
	public List<Event> getUpcomingEvent(){
		return this.eventService.findUpcomingEvent();
	}
	
	@GetMapping(value = "/get/timeline", params= {"eventId"})
	public List<Timeline> getEventTimeline(@RequestParam("eventId") int eventId){
		return this.eventService.getEventTimeline(eventId);
	}
	
//	@GetMapping(value = "/delete", params= {"eventId", "username"})
//	public EventAction deleteEvent(@RequestParam("eventId") String eventId, @RequestParam("username") String username) {
//		int id = Integer.parseInt(eventId);
//		eventService.delete(id);
//		return eventActionService.addAction(new EventAction(username, id, 2, new Date()));
//	}
	

	
	
	
}
