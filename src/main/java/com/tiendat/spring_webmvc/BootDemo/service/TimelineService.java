package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;

public interface TimelineService {

	List<TimelineInformation> getEventTimeline(int eventId);
	
	boolean addTimeline(Timeline timeline, String username);
	
	boolean updateTimeline(Timeline timeline, String username);
}
