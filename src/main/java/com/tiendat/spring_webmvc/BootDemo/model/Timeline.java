package com.tiendat.spring_webmvc.BootDemo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "timeline")
public class Timeline {
	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private int eventId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnd;
	
	public Timeline() {
		super();
	}
	
	public Timeline(String name, String description, int eventId, Date dateStart, Date dateEnd) {
		super();
		this.name = name;
		this.description = description;
		this.eventId = eventId;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public Timeline(int id, String name, String description, int eventId, Date dateStart, Date dateEnd) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
