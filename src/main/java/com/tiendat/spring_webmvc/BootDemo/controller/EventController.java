package com.tiendat.spring_webmvc.BootDemo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping(value = "/all")
	public List<Event> getAllEvent() {
		return eventService.findAllEvent();
	}

	// @GetMapping(value = "/new", params = {"name", "dateStart", "dateEnd"
	// ,"description"})
	// public Event newEvent(
	// @RequestParam("name") String name,
	// @RequestParam("dateStart") String dateStart,
	// @RequestParam("dateEnd") String dateEnd,
	// @RequestParam("description") String description
	// ) {
	// Event event = new Event();
	// event.setName(name);
	// event.setDateStart(Date.valueOf(dateStart));
	//
	// event.setDateEnd(Date.valueOf(dateEnd));
	// event.setDescription(description);
	// return eventService.insertEvent(event);
	// }

	@GetMapping(value = "/new", params = { "name", "dateStart", "dateEnd", "description" })
	public Event newEvent(@ModelAttribute("abc") Event event) {

		return eventService.insertEvent(event);
	}
	
	@GetMapping(value = "/get/{name}")
	public List<Event> getByName(@PathVariable String name){
		return eventService.findEventByName(name);
	}
}
