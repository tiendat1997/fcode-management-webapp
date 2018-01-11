package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiendat.spring_webmvc.BootDemo.model.Timeline;

public interface TimelineRepository extends JpaRepository<Timeline, Long>{

	Timeline findById(int id);
	
	List<Timeline> findByEventId(int eventId);
	
	Timeline save(Timeline timeline);
	
	@Transactional
	Long deleteById(int id);
	
	@Query("select t from timeline t where eventId in (select eventId from event where categoryId in (3,4))")
	List<Timeline> getListScheduler();
	
	@Query("select t from timeline t where MONTH(dateStart) = ?1 and YEAR(dateStart) = ?2 and eventId in (select eventId from event where categoryId in (3,4))")
	List<Timeline> getListSchedulerByMonth(int month, int year);
	
}
