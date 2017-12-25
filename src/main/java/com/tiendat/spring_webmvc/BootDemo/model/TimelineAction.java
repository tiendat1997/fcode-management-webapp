package com.tiendat.spring_webmvc.BootDemo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "action_timeline")
public class TimelineAction {

	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int timelineId;
	private String memberId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int action;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(int timelineId) {
		this.timelineId = timelineId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
