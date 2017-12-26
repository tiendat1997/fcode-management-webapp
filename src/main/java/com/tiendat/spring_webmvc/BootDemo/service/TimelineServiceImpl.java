package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineAction;
import com.tiendat.spring_webmvc.BootDemo.respository.EventRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineActionRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineRepository;

@Service("timelineService")
@Transactional
public class TimelineServiceImpl implements TimelineService{

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private TimelineRepository timelineRepository;
	
	@Autowired 
	private TimelineActionRepository timelineActionRepository; 
	
	@Override
	public List<Timeline> getEventTimeline(int eventId) {
		return this.timelineRepository.findByEventId(eventId);
	}

	@Override
	public boolean addTimeline(Timeline timeline, String username) {
		Event event = eventRepository.findByEventId(timeline.getEventId());
		long t = timeline.getDate().getTime();
		if (t >= event.getDateStart().getTime() && t <= event.getDateEnd().getTime()) {
			timelineRepository.save(timeline);
			timelineActionRepository.save(new TimelineAction(timeline.getId(), username, new Date(), 1));
			return true;
		}
			
		return false;
	}

	
	
	@Override
	public boolean updateTimeline(Timeline timeline, String username) {
		
		Event event = eventRepository.findByEventId(timeline.getEventId());
		long t = timeline.getDate().getTime();
		if (t >= event.getDateStart().getTime() && t <= event.getDateEnd().getTime()) {
			timelineRepository.save(timeline);
			timelineActionRepository.save(new TimelineAction(timeline.getId(), username, new Date(), 3));
			return true;
		}
			
		return false;
	}
	
}
