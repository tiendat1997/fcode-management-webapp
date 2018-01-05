package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.EventCategory;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long>{

	EventCategory findById(int id);
	
	
}
