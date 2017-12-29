package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;
import com.tiendat.spring_webmvc.BootDemo.service.ProjectService;

@RestController
@RequestMapping("/admin/api/project")
public class ProjectController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/get/all")
    public List<ProjectInformation> getAll() {
        return this.projectService.findAllProject();
    }

    @GetMapping(value = "/get/id", params = "projectId")
    public ProjectInformation getProjectById(@RequestParam("projectId") int projectId) {
        return this.projectService.findProjectById(projectId);
    }
    
    @GetMapping(value = "/get/public")
    public List<ProjectInformation> getPublicProject(){
    	return this.projectService.findPublicProject();
    }
    
    @GetMapping(value = "/get/notpublic")
    public List<ProjectInformation> getNotPublicProject(){    
    	return this.projectService.findNotPublicProject();
    }
    
    @GetMapping(value = "/update", params= {"projectId", "notPublic"})
    public String approveProject(@RequestParam("projectId") int projectId, @RequestParam("notPublic") boolean notPublic){
    	boolean result = this.projectService.approveProject(projectId, notPublic);
    	if (result) {
    		return SUCCESS;
    	}
    	return FAIL;
    }

}
