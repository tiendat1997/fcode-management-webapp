package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;
import com.tiendat.spring_webmvc.BootDemo.model.UserEvent;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
import com.tiendat.spring_webmvc.BootDemo.service.ProjectService;
import com.tiendat.spring_webmvc.BootDemo.service.TimelineService;

@RestController
@RequestMapping("/user/api")
public class UserRestController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private TimelineService timelineService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AccountService accountService;
	
//	*****************************
//	Event
//	*****************************
	
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
	
//	*****************************
//	Project
//	*****************************
	@GetMapping(value = "/project/get/own")
	public List<ProjectInformation> getListOwnProject(HttpSession session){
		String memberId = getUsername(session);
		System.out.println(memberId);
		if (memberId != null) {
			return this.projectService.findMemberProject(memberId);	
		}
		return null;
	}
	
	@GetMapping(value = "/project/get/participant")
	public List<ProjectInformation> getListParticitpantProject(HttpSession session){
		String memberId = getUsername(session);
		System.out.println(memberId);
		if (memberId != null) {
			return this.projectService.getListProjectParticipant(memberId);
		}
			
		return null;
	}
	
	
	
	
//	**************************
//	Profile
//	**************************
	
	@GetMapping(value="/profile/get")
	public Account getProfile(HttpSession session) {
		String memberId = getUsername(session);
		if (session != null) {
			return this.accountService.findByUsername(memberId);
		}
		return null;
	}
	
	// *************************
    // JUST FOR TEST
    // ***************************

    private String getUsername(HttpSession session) {
        String username = null;
        if (session != null) {
            Account account = (Account) session.getAttribute("account");
           if (account == null) {
        	   username = "tiendat";
           }
           else {
        	   username = account.getUsername(); 
           }
        	   
        }else {
        	username = "tiendat";
        }
        
        return username;
    }
}
