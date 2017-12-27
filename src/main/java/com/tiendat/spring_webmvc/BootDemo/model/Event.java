package com.tiendat.spring_webmvc.BootDemo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "event")
public class Event {

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;
	private String name;
	private Date dateStart;
	private Date dateEnd;
	private String description;
	private boolean notPublic;
	private int categoryId;

	public Event() {
		super();
	}

	

	public Event(String name, Date dateStart, Date dateEnd, String description, boolean notPublic, int categoryId) {
		super();
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.description = description;
		this.notPublic = notPublic;
		this.categoryId = categoryId;
	}



	public Event(int eventId, String name, Date dateStart, Date dateEnd, String description, boolean notPublic,
			int categoryId) {
		super();
		this.eventId = eventId;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.description = description;
		this.notPublic = notPublic;
		this.categoryId = categoryId;
	}



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public boolean isNotPublic() {
		return notPublic;
	}

	public void setNotPublic(boolean notPublic) {
		this.notPublic = notPublic;
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

}
