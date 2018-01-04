package com.tiendat.spring_webmvc.BootDemo.model;

public class UserEvent {

	private int eventId;
	private String name;
	private String dateStart;
	private String dateEnd;
	private String description;
	
	private EventCategory eventCategory;

	
	
	public UserEvent(int eventId, String name, String dateStart, String dateEnd, String description,
			EventCategory eventCategory) {
		super();
		this.eventId = eventId;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.description = description;
		this.eventCategory = eventCategory;
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

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	
	
}
