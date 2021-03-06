package com.tiendat.spring_webmvc.BootDemo.model;

import java.sql.Date;
import java.util.List;

public class EventInformation {

	private int eventId;
	private String name;
	private String dateStart;
	private String dateEnd;
	private String description;
	private boolean notPublic;
	
	private EventCategory eventCategory;
	private List<TimelineInformation> timelines;

	
	
	

	public EventInformation(int eventId, String name, String dateStart, String dateEnd, String description,
			boolean notPublic, EventCategory eventCategory, List<TimelineInformation> timelines) {
		super();
		this.eventId = eventId;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.description = description;
		this.notPublic = notPublic;
		this.eventCategory = eventCategory;
		this.timelines = timelines;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isNotPublic() {
		return notPublic;
	}

	public void setNotPublic(boolean notPublic) {
		this.notPublic = notPublic;
	}

	public List<TimelineInformation> getTimelines() {
		return timelines;
	}

	public void setTimelines(List<TimelineInformation> timelines) {
		this.timelines = timelines;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}

	
	
	
}
