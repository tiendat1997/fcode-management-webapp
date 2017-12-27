package com.tiendat.spring_webmvc.BootDemo.model;

import java.util.Date;

public class TimelineInformation {

	private int id;
	private String name;
	private String description;
	private int eventId;
	private String dateStart;
	private String dateEnd;

	
	
	public TimelineInformation(int id, String name, String description, int eventId, String dateStart, String dateEnd) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.eventId = eventId;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
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

}
