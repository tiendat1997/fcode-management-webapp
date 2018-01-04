package com.tiendat.spring_webmvc.BootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tiendat.spring_webmvc.BootDemo.respository.AccountRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountRepository userRespository;
    
    
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
    
    @GetMapping(value = "/project")
    public ModelAndView getProjectPage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userProject");
    	return mv;    	
    }
    
    @GetMapping(value = "/profile")
    public ModelAndView getProfilePage(ModelMap modelMap) {
    	ModelAndView mv = new ModelAndView("userProfile");
    	return mv;    	
    }

}
