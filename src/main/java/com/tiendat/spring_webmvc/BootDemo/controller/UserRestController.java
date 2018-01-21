package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Category;
import com.tiendat.spring_webmvc.BootDemo.model.Project;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;
import com.tiendat.spring_webmvc.BootDemo.model.UserAccount;
import com.tiendat.spring_webmvc.BootDemo.model.UserEvent;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;
import com.tiendat.spring_webmvc.BootDemo.service.EventService;
import com.tiendat.spring_webmvc.BootDemo.service.ProjectService;
import com.tiendat.spring_webmvc.BootDemo.service.TimelineService;

@RestController
@RequestMapping("/user/api")
public class UserRestController {
	
	private static final String FAIL="fail";
	private static final String SUCCESS = "success";

	@Autowired
	private EventService eventService;
	
	@Autowired
	private TimelineService timelineService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AccountService accountService;
	
//	*****************************
//	Scheduler
//	*****************************
	
	@GetMapping(value = "/scheduler/get/timeline")
	public List<TimelineInformation> getListTimeline(){
		return this.timelineService.getListTimelineScheduler();
	}
	
	@GetMapping(value = "/scheduler/get/timeline",params= {"month","year"})
	public List<TimelineInformation> getListTimelineByMonth(@RequestParam("month") int month, @RequestParam("year") int year){
		return this.timelineService.getListTimelineSchedulerByMonth(month, year);
	}
	
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
	
	@GetMapping(value = "/project/add", params= {"name","description","link","imgUrl","notPublic","categories","participants"})
	public String addProject(
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("link") String link,
			@RequestParam("imgUrl") String imgUrl,
			@RequestParam("notPublic") boolean notPublic,
			@RequestParam("categories") int[] categories,
			@RequestParam("participants") String[] participants,
			HttpSession session) {
		String memberId = getUsername(session);
		if (memberId != null) {
			Project project = new Project(name, description, memberId, link, imgUrl, notPublic);
			boolean result = this.projectService.addProject(project, categories, participants);
			if (result)
				return SUCCESS;
		}
		
		return FAIL;
	}
	
	@GetMapping(value = "/project/update", params= {"projectId","name","description","link","imgUrl","notPublic","categories","participants"})
	public String addProject(
			@RequestParam("projectId") int projectId,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("link") String link,
			@RequestParam("imgUrl") String imgUrl,
			@RequestParam("notPublic") boolean notPublic,
			@RequestParam("categories") int[] categories,
			@RequestParam("participants") String[] participants,
			HttpSession session) {
		String memberId = getUsername(session);
		if (memberId != null) {
			Project project = new Project(projectId,name, description, memberId, link, imgUrl, notPublic);
			boolean result = this.projectService.updateProject(project, categories, participants);
			if (result)
				return SUCCESS;
		}
		
		return FAIL;
	}
	
	@GetMapping(value = "/category/get/all")
	public List<Category> getListCategory(){
		return this.projectService.getListCategory();
	}
	
	@GetMapping(value = "/project/add/collaborators", params= {"projectId", "members"})
	public String addCollaborators(@RequestParam("projectId") int projectId, @RequestParam("members") String[] members) {
		boolean result = projectService.addCollaborators(members, projectId);
		if (result) {
			return SUCCESS;
		}
		return FAIL;
	}
	
//	@GetMapping(value = "/project/add/collaborators", params= {"projectId", "studentCodes"})
//	public String addCollaboratorsUsingStudentCode(@RequestParam("projectId") int projectId, @RequestParam("studentCodes") String[] studentCodes) {
//		boolean result = projectService.addCollaboratorsUsingStudentCode(studentCodes, projectId);
//		if (result) {
//			return SUCCESS;
//		}
//		return FAIL;
//	}
	
	@GetMapping(value = "/project/delete/collaborators", params= {"projectId", "member"})
	public String deleteCollaborators(@RequestParam("projectId") int projectId, @RequestParam("member") String member) {
		boolean result = projectService.deleteCollaborator(member, projectId);
		if (result) {
			return SUCCESS;
		}
		return FAIL;
	}
	
	@GetMapping(value = "/project/find/collaborators/fullname", params= {"name"})
	public List<UserAccount> findCollaboratorsUsingFullname(@RequestParam("name") String name) {
		return accountService.findTop10ByFullnameForUser(name);
	}
	
	@GetMapping(value = "/project/find/collaborators/username", params= {"username"})
	public List<UserAccount> findCollaboratorsUsingUsername(@RequestParam("username") String username) {
		return accountService.findTop10ByUsernameForUser(username);
	}
	
	
	@GetMapping(value = "/project/get/collaborators", params = {"projectId"})
	public List<UserAccount> getProjectListCollaborators(@RequestParam("projectId") int projectId){
		return projectService.getListProjectCollaborators(projectId);
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
	
	@GetMapping(value = "/profile/update", params= {"password","fullname","email","phone","imageUrl","grade","major"})
	public String updateProfile(
			HttpSession session,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("imageUrl") String imageUrl,
			@RequestParam("grade") int grade,
			@RequestParam("majow") String major) {
		
		
		String username = getUsername(session);
		if (username != null) {
			Account account = accountService.findByUsername(username);
			accountService.updateAccount(new Account(username, password, fullname, email, phone, account.getRoleId(), account.getStudentCode(), account.isExpired()));
			return SUCCESS;
		}
		return FAIL;
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
