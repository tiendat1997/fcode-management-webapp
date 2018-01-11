package com.tiendat.spring_webmvc.BootDemo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Event;
import com.tiendat.spring_webmvc.BootDemo.model.EventInformation;
import com.tiendat.spring_webmvc.BootDemo.model.Timeline;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineAction;
import com.tiendat.spring_webmvc.BootDemo.model.TimelineInformation;
import com.tiendat.spring_webmvc.BootDemo.respository.EventRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineActionRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.TimelineRepository;

@Service("timelineService")
@Transactional
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private TimelineActionRepository timelineActionRepository;

    private TimelineInformation getAllInfomation(Timeline timeline) {
        SimpleDateFormat format = new SimpleDateFormat("M/d/y h:m a");
        return new TimelineInformation(timeline.getId(), timeline.getName(), timeline.getDescription(),
                timeline.getEventId(), format.format(timeline.getDateStart()), format.format(timeline.getDateEnd()));
    }

    private List<TimelineInformation> getAllListInfomation(List<Timeline> timelines) {
        List<TimelineInformation> listTimelines = null;
        for (Timeline timeline : timelines) {
            if (listTimelines == null) {
                listTimelines = new ArrayList<>();
            }
            listTimelines.add(getAllInfomation(timeline));
        }
        return listTimelines;
    }

    @Override
    public List<TimelineInformation> getEventTimeline(int eventId) {
        List<Timeline> timelines = this.timelineRepository.findByEventId(eventId);
        return this.getAllListInfomation(timelines);
    }

    private boolean isValidDateStarDateEnd(Timeline timeline) {
        Event event = eventRepository.findByEventId(timeline.getEventId());
        long eStart = event.getDateStart().getTime();
        long eEnd = event.getDateEnd().getTime();
        long tStart = timeline.getDateStart().getTime();
        long tEnd = timeline.getDateEnd().getTime();
        if (eStart < tStart && eEnd > tStart) {
            if (eStart < tEnd && eEnd > tEnd) {
                if (tStart < tEnd) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean addTimeline(Timeline timeline, String username) {
        if (isValidDateStarDateEnd(timeline)) {
            timelineRepository.save(timeline);
            timelineActionRepository.save(new TimelineAction(timeline.getId(), username, new Date(), 1));
            return true;
        }

        return false;
    }

    @Override
    public boolean updateTimeline(Timeline timeline, String username) {
        if (isValidDateStarDateEnd(timeline)) {
            timelineRepository.save(timeline);
            timelineActionRepository.save(new TimelineAction(timeline.getId(), username, new Date(), 3));
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteTimeline(int id, String username) {
        Long result = this.timelineRepository.deleteById(id);
        if (result > 0) {
            timelineActionRepository.save(new TimelineAction(id, username, new Date(), 2));
            return true;
        }

        return false;
    }

    @Override
    public TimelineInformation getById(int id) {
        return getAllInfomation(timelineRepository.findById(id));
    }

	@Override
	public List<TimelineInformation> getListTimelineScheduler() {
		List<Timeline> timelines = this.timelineRepository.getListScheduler();
		return getAllListInfomation(timelines);
	}

	@Override
	public List<TimelineInformation> getListTimelineSchedulerByMonth(int month, int year) {
		List<Timeline> timelines = this.timelineRepository.getListSchedulerByMonth(month, year);
		return getAllListInfomation(timelines);
	}

}
