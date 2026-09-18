package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugur.entity.Category;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findByCategoryId(String categoryId);
	
}
