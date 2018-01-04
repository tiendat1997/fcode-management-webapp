package com.tiendat.spring_webmvc.BootDemo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventAction;
import com.tiendat.spring_webmvc.BootDemo.model.EventCategory;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.UserEvent;
import com.tiendat.spring_webmvc.BootDemo.respository.EventActionRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.EventCategoryRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.EventRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineRepository;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private EventActionRepository eventActionRepository;

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    private EventInformation getAllInfomation(Event event) {
        SimpleDateFormat format = new SimpleDateFormat("M/d/y");
        return new EventInformation(
                event.getEventId(),
                event.getName(),
                format.format(event.getDateStart()),
                format.format(event.getDateEnd()),
                event.getDescription(),
                event.isNotPublic(),
                eventCategoryRepository.findById(event.getCategoryId()),
                timelineService.getEventTimeline(event.getEventId()));
    }

    private List<EventInformation> getAllListInfomation(List<Event> events) {
        List<EventInformation> listEvents = null;
        for (Event event : events) {
            if (listEvents == null) {
                listEvents = new ArrayList<>();
            }
            listEvents.add(getAllInfomation(event));
        }
        return listEvents;
    }

    @Override
    public List<EventInformation> findAllEvent() {
        List<Event> events = this.eventRepository.findAll();
        return this.getAllListInfomation(events);
    }

    @Override
    public List<EventInformation> findEventByName(String name) {
        List<Event> events = eventRepository.findByNameContaining(name);
        return this.getAllListInfomation(events);
    }

    @Override
    public List<EventInformation> findAllPublicEvent() {
        List<Event> events = this.eventRepository.findByNotPublicIsFalse();
        return this.getAllListInfomation(events);
    }

    @Override
    public List<EventInformation> findOldEvent() {
        List<Event> events = this.eventRepository.findByDateEndBefore(new Date(System.currentTimeMillis()));
        return this.getAllListInfomation(events);
    }

    @Override
    public List<EventInformation> findCurrentEvent() {
        List<Event> events = this.eventRepository.findByDateStartBeforeAndDateEndAfter(new Date(System.currentTimeMillis()));
        return this.getAllListInfomation(events);
    }

    @Override
    public List<EventInformation> findUpcomingEvent() {
        List<Event> events = this.eventRepository.findByDateStartAfterOrderByDateStartAsc(new Date(System.currentTimeMillis()));
        return this.getAllListInfomation(events);
    }

    @Override
    public EventInformation findEventById(int eventId) {
        Event event = eventRepository.findByEventId(eventId);
        return this.getAllInfomation(event);
    }

    private boolean isValidDate(Event event) {
        long dStart = event.getDateStart().getTime();
        long dEnd = event.getDateEnd().getTime();
        long now = new Date(System.currentTimeMillis()).getTime();
        
        if (now - dStart < (1000*60*60*24)) {
            if (dEnd >= dStart) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean insertEvent(Event event, String username) {
        if (isValidDate(event)) {
            Event e = eventRepository.saveAndFlush(event);
            //add default timeline for event
            Timeline timeline = new Timeline(event.getName(), "", event.getEventId(), event.getDateStart(), event.getDateEnd());
            timelineRepository.save(timeline);
            EventAction action = new EventAction(username, e.getEventId(), 1, new java.util.Date());
            this.eventActionRepository.save(action);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Event event, String username) {
        if (isValidDate(event)) {
            EventAction action = new EventAction(username, event.getEventId(), 3, new java.util.Date());
            this.eventActionRepository.save(action);
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int eventId, String username) {
        EventAction action = new EventAction(username, eventId, 2, new java.util.Date());
        Long result = eventRepository.deleteByEventId(eventId);
        if (result > 0) {
            return true;
        }
        return false;
    }

//    ************USER SERVICE****************
    private UserEvent getEventForUser(Event event) {
    	EventCategory category = eventCategoryRepository.findById(event.getCategoryId());
    	SimpleDateFormat format = new SimpleDateFormat("d/M/y");
    	return new UserEvent(
    			event.getEventId(), 
    			event.getName(),
    			format.format(event.getDateStart()),
    			format.format(event.getDateEnd()), 
    			event.getDescription(), 
    			category);
    }
    
    private List<UserEvent> getListEventForUser(List<Event> events){
    	List<UserEvent> eventsForUser = null;
    	for (Event event: events) {
    		if (eventsForUser == null) {
    			eventsForUser = new ArrayList<>();
    		}
    		eventsForUser.add(getEventForUser(event));
    	}
    	return eventsForUser;
    }
    
	@Override
	public List<UserEvent> getUpcommingEventForUser(int duration) {
		Date now = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, duration);
		Date toDate = new Date(c.getTimeInMillis());
		System.out.println(now.toString());
		System.out.println(1000*60*60*24*duration);
		List<Event> events = eventRepository.findUpcommingEvent(
				now,
				toDate);
		return getListEventForUser(events);
	}

	@Override
	public List<UserEvent> getCurrentEventForUser() {
		List<Event> events = eventRepository.findCurrentEvent(new Date(System.currentTimeMillis()));
		return getListEventForUser(events);
	}

	
	
}
