package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Picture;
import com.tiendat.spring_webmvc.BootDemo.service.PictureService;

@RestController
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@GetMapping(value = "/all")
	public List<Picture> getAll(){
		return this.pictureService.findAllPicture();
	}
	
	@GetMapping(value = "/get/{eventId}")
	public List<Picture> getByEventId(@PathVariable int eventId){
		return this.pictureService.findPictureByEventId(eventId);
	}
	
	@GetMapping(value = "/upload", params= {"name","src","eventId"})
	public Picture uploadPicture(@ModelAttribute Picture picture) {
		return this.pictureService.uploadPicture(picture);
	}
	
	@GetMapping(value = "/delete", params= {"id"})
	public int deletePicture(@RequestParam("id") String id) {
		return this.pictureService.deletePicture(Integer.parseInt(id));
	}
	
}
