package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;
import com.tiendat.spring_webmvc.BootDemo.model.UserEvent;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
import com.tiendat.spring_webmvc.BootDemo.service.TimelineService;

@RestController
@RequestMapping("user/api")
public class UserRestController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private TimelineService timelineService;
	
	@GetMapping(value="/event/get/upcomming", params= {"duration"})
	public List<UserEvent> getEventsUpcomming(@RequestParam("duration") int duration){
		return this.eventService.getUpcommingEventForUser(duration);
	}
	
	@GetMapping(value = "/event/get/current")
	public List<UserEvent> getEventsCurrent(){
		return this.eventService.getCurrentEventForUser();
	}
	
	@GetMapping(value = "/timeline/get/event", params= {"eventId"})
	public List<TimelineInformation> getListTimelineOfEvent(@RequestParam("eventId") int eventId){
		return this.timelineService.getEventTimeline(eventId);
	}
	
	
}
