package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
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
	
	
	
	
	@GetMapping(value = "/project")
	public String getProjectPage(ModelMap modelMap) {
		return "adminProject";		
	}
	
	
	
	
}
