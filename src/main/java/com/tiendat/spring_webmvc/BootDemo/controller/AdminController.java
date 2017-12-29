package com.tiendat.spring_webmvc.BootDemo.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tiendat.spring_webmvc.BootDemo.error.EventError;
import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
import com.tiendat.spring_webmvc.BootDemo.service.ProjectService;
import com.tiendat.spring_webmvc.BootDemo.service.TimelineService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EventService eventService;
		
	@Autowired
	private TimelineService timelineSerivce;
	
	@Autowired
	private ProjectService projectService; 
	
	
	@GetMapping(value = "/dashboard")
	public String getDashboardPage(ModelMap modelMap) {
		return "adminHome";
	}
	
	@GetMapping(value = "/member")
	public String getMemberPage(ModelMap modelMap) {
		return "adminMember";
	}
	@GetMapping(value = "/member/update", params = {"username"})
	public ModelAndView updateMemberPage(@RequestParam("username") String username) {
		Account member = this.accountService.findByUsername(username); 
		ModelAndView mv = new ModelAndView("adminMemberUpdate"); 
		mv.addObject("member", member);
				
		return mv;
	}
	@PostMapping(value = "/member/update")
	public ModelAndView updateMemberPage(@ModelAttribute("member") Account member) {
						
		int result = this.accountService.updateMemberAdmin(
					member.getUsername(), member.getFullname(), member.getGrade(), 
					member.getMajor(), member.getEmail(), member.getPhone());
		
		ModelAndView mv = null;
		if (result > 0) {
			mv = new ModelAndView("adminMember");
		}
		else {
			mv = new ModelAndView("adminMemberUpdate");			
		}		
		return mv; 					
	}
	
	
	
	@GetMapping(value = "/member/admin")
	public String getListAdminPage(ModelMap modelMap) {
		
		return "adminMemberAdmin";
	}
	@GetMapping(value = "/member/newbies")
	public String getAddNewbiesPage(ModelMap modelMap) {
		return "adminMemberNew";
	}	
	
	@GetMapping(value = "/member/grade")
	public String getMemberGradePage(ModelMap modelMap) {
		return "adminMemberGrade";
	}
			
	
	@GetMapping(value = "/event")
	public String getEventPage(ModelMap modelMap) {
		return "adminEvent";
	}
	@GetMapping(value = "/event/edit", params = {"eventId"})
	public ModelAndView editEventPage(@RequestParam("eventId") Integer eventId) {
			EventInformation event = this.eventService.findEventById(eventId); 			
			
			ModelAndView mv = new ModelAndView("adminEventEdit");
			mv.addObject("event", event); 
			 			
			return mv; 			
	}
	
	@GetMapping(value = "/event/new")
	public ModelAndView newEventPage() {
		ModelAndView mv = new ModelAndView("adminEventNew");
		Event event = new Event();
		mv.addObject("event", event);
		
		return mv; 
	}
	@PostMapping(value = "/event/new", params = { "name", "dateStart", "dateEnd", "description", "notPublic", "categoryId" })
	public ModelAndView addEvent(@RequestParam("name") String name, @RequestParam("dateStart") String dateStart,
			@RequestParam("dateEnd") String dateEnd, @RequestParam("description") String description,
			@RequestParam("notPublic") boolean notPublic, @RequestParam("categoryId") int categoryId, HttpSession session) {
		String username = getUsername(session);
		SimpleDateFormat format = new SimpleDateFormat("M/d/y");
		java.util.Date dStart, dEnd;
			
		Event event = new Event();
		try {
			dStart = format.parse(dateStart);
			dEnd = format.parse(dateEnd);
			event = new Event(name, new Date(dStart.getTime()), new Date(dEnd.getTime()), description, notPublic,categoryId);
			
			boolean result = this.eventService.insertEvent(event, username);
			if (result) {
				return new ModelAndView("adminEvent");
			}					
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		EventError error = new EventError(); 
		error.setInvalidDate("Invalid Date Event - Please Check Start and End Date");
		ModelAndView mv = new ModelAndView("adminEventNew");		
		mv.addObject("event", event);
		mv.addObject("error", error);
		
		return mv;
	}
	
	
	@GetMapping(value = "/timeline/edit", params = {"id"})
	public ModelAndView editTimelinePage(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("adminTimelineEdit");
		
		TimelineInformation timeline = this.timelineSerivce.getById(id);
		EventInformation event = this.eventService.findEventById(timeline.getEventId());
		
		mv.addObject("event", event);
		mv.addObject("timeline", timeline);
		
				
		return mv;
	}
	
	@GetMapping(value = "/project")
	public String getProjectPage(ModelMap modelMap) {
		return "adminProject";		
	}
	
	@GetMapping(value = "/project/details", params = {"projectId"})
	public ModelAndView getProjectDetailsPage(@RequestParam("projectId") int id) {
		ProjectInformation projectInfo = this.projectService.findProjectById(id);
		
		Account leader = this.accountService.findByUsername(projectInfo.getProject().getMemberId());
		
		ModelAndView mv = new ModelAndView("adminProjectDetails");			
		mv.addObject("projectInfo", projectInfo); 
		mv.addObject("leader", leader);
		
		return mv; 
				
	}
	
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
	
	
	
}
