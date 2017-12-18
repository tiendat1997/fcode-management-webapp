package com.tiendat.spring_webmvc.BootDemo.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.EventAction;
import com.tiendat.spring_webmvc.BootDemo.model.PictureAction;
import com.tiendat.spring_webmvc.BootDemo.model.PostAction;
import com.tiendat.spring_webmvc.BootDemo.service.EventActionService;
import com.tiendat.spring_webmvc.BootDemo.service.PictureActionService;
import com.tiendat.spring_webmvc.BootDemo.service.PostActionService;

@RestController
@RequestMapping("/action")
public class HistoryController {

	@Autowired
	private EventActionService eventActionService;
	
	@Autowired
	private PictureActionService pictureActionSerivce;
	
	@Autowired
	private PostActionService postActionService;
//	******************
//	event history
//	******************
	@GetMapping(value = "/event/all")
	public List<EventAction> getAllEventHistory(){
		return this.eventActionService.findAll();
	}
	
	@GetMapping(value = "/event/member/{memberId}")
	public List<EventAction> getEventHistoryOfMember(@PathVariable("memberId") String username) {
		return this.eventActionService.findActionByMemberId(username);
	}
	
	@GetMapping(value = "/event/event/{eventId}")
	public List<EventAction> getEventHistoryOfEvent(@PathVariable("eventId") int eventId) {
		return this.eventActionService.findActionByEventId(eventId);
	}
	@GetMapping(value = "/event/action/{typeAction}")
	public List<EventAction> getEventHistoryOfAction(@PathVariable("typeAction") int action) {
		return this.eventActionService.findActionByAction(action);
	}
	@GetMapping(value = "/event/date/{date}")
	public List<EventAction> getEventHistoryOfDate(@PathVariable("date") Timestamp date) {
		return this.eventActionService.findActionByDate(date);
	}

//	****************
//	picture history
//	****************
	
	@GetMapping(value = "/picture/all")
	public List<PictureAction> getAllPictureHistory(){
		return this.pictureActionSerivce.findAll();
	}
	
	@GetMapping(value = "/picture/member/{memberId}")
	public List<PictureAction> getPictureHistoryOfMember(@PathVariable("memberId") String username) {
		return this.pictureActionSerivce.findActionByMemberId(username);
	}
	
	@GetMapping(value = "/picture/picture/{pictureId}")
	public List<PictureAction> getPictureHistoryOfEvent(@PathVariable("pictureId") int pictureId) {
		return this.pictureActionSerivce.findActionByPictureId(pictureId);
	}
	@GetMapping(value = "/picture/action/{typeAction}")
	public List<PictureAction> getPictureHistoryOfAction(@PathVariable("typeAction") int action) {
		return this.pictureActionSerivce.findActionByAction(action);
	}
	@GetMapping(value = "/picture/date/{date}")
	public List<PictureAction> getPictureHistoryOfDate(@PathVariable("date") Timestamp date) {
		return this.pictureActionSerivce.findActionByDate(date);
	}
//	****************
//	post history
//	****************
	
	@GetMapping(value = "/post/all")
	public List<PostAction> getAllPostHistory(){
		return this.postActionService.findAll();
	}
	
	@GetMapping(value = "/post/member/{memberId}")
	public List<PostAction> getPosteHistoryOfMember(@PathVariable("memberId") String username) {
		return this.postActionService.findPostByMemberId(username);
	}
	
	@GetMapping(value = "/post/post/{postId}")
	public List<PostAction> getPostHistoryOfEvent(@PathVariable("postId") int postId) {
		return this.postActionService.findPostByPostId(postId);
	}
	@GetMapping(value = "/post/action/{typeAction}")
	public List<PostAction> getPostHistoryOfAction(@PathVariable("typeAction") int action) {
		return this.postActionService.findPostByAction(action);
	}
	@GetMapping(value = "/post/date/{date}")
	public List<PostAction> getPostistoryOfDate(@PathVariable("date") Timestamp date) {
		return this.postActionService.findPostByDate(date);
	}

}
