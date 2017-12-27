package com.tiendat.spring_webmvc.BootDemo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventAction;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.service.EventActionService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
import com.tiendat.spring_webmvc.BootDemo.service.TimelineService;

@RestController
@RequestMapping("admin/api/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private EventActionService eventActionService;

	@Autowired
	private TimelineService timelineService;

	@GetMapping(value = "/all")
	public List<Event> getAllEvent() {
		return this.eventService.findAllEvent();
	}

	@GetMapping(value = "/get/{name}")
	public List<Event> getByName(@PathVariable String name) {
		return this.eventService.findEventByName(name);
	}

	@GetMapping(value = "/public")
	public List<Event> getPublicEvent() {
		return this.eventService.findAllPublicEvent();
	}

	@GetMapping(value = "/get/id/{eventId}")
	public Event getEventById(@PathVariable("eventId") int eventId) {
		return this.eventService.findEventById(eventId);
	}

	@GetMapping(value = "/get/old")
	public List<Event> getOldEvent() {
		return this.eventService.findOldEvent();
	}

	@GetMapping(value = "/get/current")
	public List<Event> getCurrentEvent() {
		return this.eventService.findCurrentEvent();
	}

	@GetMapping(value = "/get/upcoming")
	public List<Event> getUpcomingEvent() {
		return this.eventService.findUpcomingEvent();
	}

	@GetMapping(value = "/get/timeline", params = { "eventId" })
	public List<Timeline> getEventTimeline(@RequestParam("eventId") int eventId) {
		return this.timelineService.getEventTimeline(eventId);
	}

	@GetMapping(value = "/new", params = { "name", "dateStart", "dateEnd", "description", "notPublic" })
	public EventAction newEvent(@ModelAttribute Event event, HttpSession session) {
		String username = getUsername(session);
		Event e = this.eventService.insertEvent(event);
		EventAction action = new EventAction(username, e.getEventId(), 1, new java.util.Date());
		return this.eventActionService.addAction(action);
	}

	@GetMapping(value = "/update", params = { "eventId", "name", "dateStart", "dateEnd", "description", "notPublic" })
	public EventAction updateEvent(@RequestParam("eventId") int eventId, @RequestParam("name") String name,
			@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd,
			@RequestParam("description") String description, @RequestParam("notPublic") boolean notPublic,
			HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y");
		java.util.Date dStart, dEnd;
		try {
			dStart = format.parse(dateStart);
			dEnd = format.parse(dateEnd);
			Event event = new Event(eventId, username, new Date(dStart.getTime()), new Date(dEnd.getTime()),
					description, notPublic);
			Event e = this.eventService.update(event);
			EventAction action = new EventAction(username, e.getEventId(), 3, new java.util.Date());
			return this.eventActionService.addAction(action);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	@GetMapping(value = "/add/timeline", params = { "name", "description", "eventId", "dateStart", "dateEnd" })
	public boolean addTimeline(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("eventId") int eventId, @RequestParam("dateStart") String dateStart,
			@RequestParam("dateEnd") String dateEnd, HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y h:m a");
		boolean result = false;
		try {
			result = this.timelineService.addTimeline(
					new Timeline(name, description, eventId, format.parse(dateStart), format.parse(dateEnd)), username);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping(value = "/update/timeline", params = { "id", "name", "description", "eventId", "dateStart","dateEnd" })
	public boolean updateTimeline(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("eventId") int eventId,
			@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y h:m a");
		boolean result = false;
		try {
			
			result = this.timelineService.updateTimeline(new Timeline(id, name, description, eventId, format.parse(dateStart),format.parse(dateEnd)), username);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	// *************************
	// JUST FOR TEST
	// ***************************
	private String getUsername(HttpSession session) {
		String username = null;
		if (session != null) {
			Account account = (Account) session.getAttribute("account");
			if (account == null)
				username = "tiendat";
		} else {
			username = "tiendat";
		}
		return username;
	}

	// @GetMapping(value = "/delete", params= {"eventId", "username"})
	// public EventAction deleteEvent(@RequestParam("eventId") String eventId,
	// @RequestParam("username") String username) {
	// int id = Integer.parseInt(eventId);
	// eventService.delete(id);
	// return eventActionService.addAction(new EventAction(username, id, 2, new
	// Date()));
	// }

}
