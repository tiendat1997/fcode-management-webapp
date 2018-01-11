package com.tiendat.spring_webmvc.BootDemo.controller;

import static org.hamcrest.CoreMatchers.not;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Project;
import com.tiendat.spring_webmvc.BootDemo.respository.AccountRepository;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;
import com.tiendat.spring_webmvc.BootDemo.service.ProjectService;

@RestController
@RequestMapping("/user")
public class UserController {

  
    @Autowired
    private AccountService accountService; 
    
    @Autowired
    private ProjectService projectService; 
    
    
    
    @GetMapping(value = "/home")
    public ModelAndView getHomePage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userHome");
    	return mv; 
    }
    
    @GetMapping(value = "/scheduler")
    public ModelAndView getSchedulerPage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userScheduler");
    	return mv;    	
    }
    
    
    // GET UPCOMING - DEFAULT
    @GetMapping(value = "/event")
    public ModelAndView getEventPage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userEvent");
    	return mv;    	
    }
    @GetMapping(value = "/event/current")
    public ModelAndView getCurrentEventPage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userCurrentEvent");
    	return mv;    	
    }
    
    
    @GetMapping(value = "/timeline", params = {"eventId","eventName","dateStart","dateEnd"})
    public ModelAndView getTimelinePage(ModelMap modelMap, 
    			@RequestParam("eventId") Integer eventId,
    			@RequestParam("eventName") String eventName,
    			@RequestParam("dateStart") String dateStart,
    			@RequestParam("dateEnd") String dateEnd) {
    	ModelAndView mv = new ModelAndView("userTimeline"); 
    	mv.addObject("eventId", eventId);
    	mv.addObject("eventName", eventName); 
    	mv.addObject("dateStart", dateStart);
    	mv.addObject("dateEnd", dateEnd);
    	
    	
    	return mv; 
    }
    
    @GetMapping(value = "/project")
    public ModelAndView getProjectPage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userProject");
    	
    	return mv;    	
    }    
    @GetMapping(value = "/project/new")    
    public ModelAndView getNewProjectPage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userNewProject");
    	    	
    	return mv;    	
    }
    @PostMapping(value = "/project/new", params= {"name","description","link","category","notPublic","ownerId"})
    public ModelAndView createNewProject(@RequestParam("name") String name,
    			@RequestParam("description") String description,
    			@RequestParam("link") String link,
    			@RequestParam("category") int[] category,
    			@RequestParam("notPublic") boolean notPublic,
    			@RequestParam("ownerId") String ownerId,
    			HttpSession session
    		) {
    	
    	Project project = new Project(); 
    	project.setName(name);
    	project.setDescription(description);
    	project.setLink(link);
    	project.setMemberId(ownerId);
    	System.out.println("Owner ID: " + ownerId);
    	
    	
    	    
    	boolean result = this.projectService.addProject(project, category);
    	
    	
    	ModelAndView mv = new ModelAndView("redirect:/user/project");

    	if (result == false) {
    		// FAIL 
    		return new ModelAndView("redirect:/user/project/new");
    	}
    	
    	return mv;
    	
    	
    }
    @GetMapping(value = "/project/member/add", params= {"projectId"})
    public ModelAndView goToAddMemberToProject(ModelMap modelMap, @RequestParam("projectId") int projectId) {
    	ModelAndView mv = new ModelAndView("userProjectAddMember"); 
    	    	
    	return mv;
    	
    }
    
    
    // END PROJECT
    
    @GetMapping(value = "/profile")
    public ModelAndView getProfilePage(ModelMap modelMap, HttpSession session) {
    	ModelAndView mv = new ModelAndView("userProfile");
    	Account member = (Account)session.getAttribute("account"); 
    	
    	mv.addObject("member", member);
    	return mv;    	
    }
    
    @PostMapping(value = "/profile/update")
	public ModelAndView updateMemberPage(@ModelAttribute("member") Account member, HttpSession session) {
						
		int result = this.accountService.updateMemberAdmin(
					member.getUsername(), member.getFullname(), member.getGrade(), 
					member.getMajor(), member.getEmail(), member.getPhone());
		
		ModelAndView mv = null;
		if (result > 0) {
			mv = new ModelAndView("userHome");			
			session.setAttribute("account", this.accountService.findByUsername(member.getUsername()));
			
		}
		else {
			mv = new ModelAndView("userProfile");			
		}		
		return mv; 					
	}

}
