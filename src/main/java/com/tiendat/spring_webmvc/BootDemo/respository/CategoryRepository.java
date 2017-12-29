package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findAll();
	Category findByCategoryId(int id);
	
	
}
