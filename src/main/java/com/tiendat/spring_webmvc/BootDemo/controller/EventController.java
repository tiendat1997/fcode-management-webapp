package com.tiendat.spring_webmvc.BootDemo.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
import com.tiendat.spring_webmvc.BootDemo.service.TimelineService;

@RestController
@RequestMapping("admin/api/event")
public class EventController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private EventService eventService;

	@Autowired
	private TimelineService timelineService;

	// ************************************

	@GetMapping(value = "/all")
	public List<EventInformation> getAllEvent() {
		return this.eventService.findAllEvent();
	}

	@GetMapping(value = "/get/{name}")
	public List<EventInformation> getByName(@PathVariable String name) {
		return this.eventService.findEventByName(name);
	}

	@GetMapping(value = "/public")
	public List<EventInformation> getPublicEvent() {
		return this.eventService.findAllPublicEvent();
	}

	@GetMapping(value = "/get/id/{eventId}")
	public EventInformation getEventById(@PathVariable("eventId") int eventId) {
		return this.eventService.findEventById(eventId);
	}

	@GetMapping(value = "/get/old")
	public List<EventInformation> getOldEvent() {
		return this.eventService.findOldEvent();
	}

	@GetMapping(value = "/get/current")
	public List<EventInformation> getCurrentEvent() {
		return this.eventService.findCurrentEvent();
	}

	@GetMapping(value = "/get/upcoming")
	public List<EventInformation> getUpcomingEvent() {
		return this.eventService.findUpcomingEvent();
	}

	@GetMapping(value = "/get/timeline", params = { "eventId" })
	public List<TimelineInformation> getEventTimeline(@RequestParam("eventId") int eventId) {
		return this.timelineService.getEventTimeline(eventId);
	}

	// ********************************

	@GetMapping(value = "/new", params = { "name", "dateStart", "dateEnd", "description", "notPublic", "categoryId" })
	public String addEvent(@RequestParam("name") String name, @RequestParam("dateStart") String dateStart,
			@RequestParam("dateEnd") String dateEnd, @RequestParam("description") String description,
			@RequestParam("notPublic") boolean notPublic, @RequestParam("categoryId") int categoryId, HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y");
		java.util.Date dStart, dEnd;
		try {
			dStart = format.parse(dateStart);
			dEnd = format.parse(dateEnd);
			Event event = new Event(name, new Date(dStart.getTime()), new Date(dEnd.getTime()), description, notPublic,categoryId);
			this.eventService.insertEvent(event, username);
			return SUCCESS;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return FAIL;
	}

	@GetMapping(value = "/update", params = { "eventId", "name", "dateStart", "dateEnd", "description", "notPublic", "categoryId" })
	public String updateEvent(@RequestParam("eventId") int eventId, @RequestParam("name") String name,
			@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd,
			@RequestParam("description") String description, @RequestParam("notPublic") boolean notPublic,
			@RequestParam("categoryId") int categoryId, HttpSession session) {

		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y");
		java.util.Date dStart, dEnd;
		try {
			dStart = format.parse(dateStart);
			dEnd = format.parse(dateEnd);
			Event event = new Event(eventId, name, new Date(dStart.getTime()), new Date(dEnd.getTime()), description,
					notPublic,categoryId);
			this.eventService.update(event, username);
			return SUCCESS;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return FAIL;
	}

	@GetMapping(value = "/add/timeline", params = { "name", "description", "eventId", "dateStart", "dateEnd" })
	public String addTimeline(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("eventId") int eventId, @RequestParam("dateStart") String dateStart,
			@RequestParam("dateEnd") String dateEnd, HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y h:m a");
		String url = FAIL;
		try {
			boolean result = this.timelineService.addTimeline(
					new Timeline(name, description, eventId, format.parse(dateStart), format.parse(dateEnd)), username);
			if (result)
				url = SUCCESS;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return url;
	}

	@GetMapping(value = "/update/timeline", params = { "id", "name", "description", "eventId", "dateStart", "dateEnd" })
	public String updateTimeline(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("eventId") int eventId,
			@RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd, HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y h:m a");
		String url = FAIL;
		try {

			boolean result = this.timelineService.updateTimeline(
					new Timeline(id, name, description, eventId, format.parse(dateStart), format.parse(dateEnd)),
					username);
			if (result)
				url = SUCCESS;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return url;
	}

	@GetMapping(value = "/get/timeline", params= {"id"})
	public TimelineInformation getTimelineById(@RequestParam("id") int id) {
		return this.timelineService.getById(id);
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
