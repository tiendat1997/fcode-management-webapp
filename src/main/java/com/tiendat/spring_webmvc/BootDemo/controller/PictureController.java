package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Picture;
import com.tiendat.spring_webmvc.BootDemo.model.PictureAction;
import com.tiendat.spring_webmvc.BootDemo.service.PictureActionService;
import com.tiendat.spring_webmvc.BootDemo.service.PictureService;



@RestController
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private PictureActionService pictureActionService;
	
	@GetMapping(value = "/all")
	public List<Picture> getAll(){
		return this.pictureService.findAllPicture();
	}
	
	@GetMapping(value = "/get/{eventId}")
	public List<Picture> getByEventId(@PathVariable int eventId){
		return this.pictureService.findPictureByEventId(eventId);
	}
	
	@GetMapping(value = "/upload", params= {"name","src","eventId","username"})
	public PictureAction uploadPicture(@ModelAttribute Picture picture, @RequestParam("username") String username) {
		this.pictureService.uploadPicture(picture);
		return this.pictureActionService.addAcion(new PictureAction(username, picture.getPictureId(), 1, new Date()));
	}
	
	@GetMapping(value = "/update", params= {"pictureId","name","src","eventId","username"})
	public PictureAction updatePicture(@ModelAttribute Picture picture, @RequestParam("username") String username) {
		this.pictureService.updatePicture(picture);
		return this.pictureActionService.addAcion(new PictureAction(username, picture.getPictureId(), 3, new Date()));
	}
	
	@GetMapping(value = "/delete", params= {"id","username"})
	public PictureAction deletePicture(@RequestParam("id") String id, @RequestParam("username") String username) {
		int pictureId = Integer.parseInt(id);
		this.pictureService.deletePicture(pictureId);
		return this.pictureActionService.addAcion(new PictureAction(username, pictureId, 2, new Date()));
	}
	
}
