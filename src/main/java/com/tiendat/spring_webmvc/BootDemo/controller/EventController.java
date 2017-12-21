package com.tiendat.spring_webmvc.BootDemo.controller;

import java.awt.event.ActionEvent;
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
import com.tiendat.spring_webmvc.BootDemo.service.EventActionService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;

@RestController
@RequestMapping("/event")
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

	@GetMapping(value = "/new", params = { "name", "dateStart", "dateEnd", "description", "username"})
	public EventAction newEvent(@ModelAttribute Event event, @RequestParam("username") String username) {

		Event e =  this.eventService.insertEvent(event);
		EventAction action = new EventAction(username, e.getEventId().intValue(), 1, new Date());
		return this.eventActionService.addAction(action);
	}
	
	@GetMapping(value = "/update", params = { "eventId","name", "dateStart", "dateEnd", "description", "username"})
	public EventAction updateEvent(@ModelAttribute Event event, @RequestParam("username") String username) {

		Event e =  this.eventService.update(event);
		EventAction action = new EventAction(username, e.getEventId().intValue(), 3, new Date());
		return this.eventActionService.addAction(action);
	}
	
//	@GetMapping(value = "/delete", params= {"eventId", "username"})
//	public EventAction deleteEvent(@RequestParam("eventId") String eventId, @RequestParam("username") String username) {
//		int id = Integer.parseInt(eventId);
//		eventService.delete(id);
//		return eventActionService.addAction(new EventAction(username, id, 2, new Date()));
//	}
	

	
	
	
}