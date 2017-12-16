package com.tiendat.spring_webmvc.BootDemo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.EventAction;
import com.tiendat.spring_webmvc.BootDemo.respository.EventActionRepository;

@Service("eventActionService")
@Transactional
public class EventActionServiceImpl implements EventActionService{

	@Autowired
	private EventActionRepository eventActionRepository;
	
	@Override
	public List<EventAction> findAll() {
		return this.eventActionRepository.findAll();
	}

	@Override
	public List<EventAction> findActionByEventId(int eventId) {
		return this.eventActionRepository.findByEventId(eventId);
	}

	@Override
	public List<EventAction> findActionByMemberId(String memberId) {
		return this.eventActionRepository.findByMemberId(memberId);
	}

	@Override
	public List<EventAction> findActionByAction(int action) {
		return this.eventActionRepository.findByAction(action);
	}

	@Override
	public List<EventAction> findActionByDate(Timestamp date) {
		return this.eventActionRepository.findByDate(date);
	}

	@Override
	public EventAction addAction(EventAction eventAction) {
		return this.eventActionRepository.save(eventAction);
	}
	
	
	
}
