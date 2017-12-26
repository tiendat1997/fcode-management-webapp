package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Timeline;

public interface TimelineService {

	List<Timeline> getEventTimeline(int eventId);
	
	boolean addTimeline(Timeline timeline, String username);
	
	boolean updateTimeline(Timeline timeline, String username);
}
