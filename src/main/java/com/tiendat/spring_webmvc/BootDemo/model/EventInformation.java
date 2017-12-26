package com.tiendat.spring_webmvc.BootDemo.model;

import java.sql.Date;
import java.util.List;

public class EventInformation {

	private int eventId;
	private String name;
	private Date dateStart;
	private Date dateEnd;
	private String description;
	private boolean notPublic;
	
	private List<Timeline> timelines;

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

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
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

	public List<Timeline> getTimelines() {
		return timelines;
	}

	public void setTimelines(List<Timeline> timelines) {
		this.timelines = timelines;
	}
	
	
	
}
