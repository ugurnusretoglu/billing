package com.ugur.service.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ugur.entity.Category;
import com.ugur.io.CategoryRequest;
import com.ugur.io.CategoryResponse;
import com.ugur.repository.CategoryRepository;
import com.ugur.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public CategoryResponse add(CategoryRequest request) {
		Category newCategory = convertToEntity(request);
		newCategory = categoryRepository.save(newCategory);
		return convertToResponse(newCategory);
	}

	private CategoryResponse convertToResponse(Category newCategory) {
		return CategoryResponse.builder()
				.categoryId(newCategory.getCategoryId())
				.name(newCategory.getName())
				.description(newCategory.getDescription())
				.bgColor(newCategory.getBgColor())
				.imgUrl(newCategory.getImgUrl())
				.createdAt(newCategory.getCreatedAt())
				.updatedAt(newCategory.getUpdatedAt())
				.build();
	}

	private Category convertToEntity(CategoryRequest request) {
		return Category.builder().
				categoryId(UUID.randomUUID().toString())
				.name(request.getName())
				.description(request.getDescription())
				.bgColor(request.getBgColor())
				.build();
	}

	@Override
	public List<CategoryResponse> read() {
		return categoryRepository.findAll()
				.stream()
				.map(category -> convertToResponse(category))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String categoryId) {
		Category existingCategory = categoryRepository.findByCategoryId(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found:"+ categoryId));
		categoryRepository.delete(existingCategory);
	}

}
