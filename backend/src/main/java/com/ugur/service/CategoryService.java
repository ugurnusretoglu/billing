package com.ugur.service;

import java.util.List;

import com.ugur.io.CategoryRequest;
import com.ugur.io.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse add(CategoryRequest request);
	
	List<CategoryResponse> read();
	
	void delete(String categoryId);
	
}
