package com.rvtjakula.resourceservice.services;

import com.rvtjakula.resourceservice.entites.Category;
import com.rvtjakula.resourceservice.dtos.CategoryDTO;
import com.rvtjakula.resourceservice.dtos.CategoryResponse;

public interface CategoryService {

	CategoryDTO createCategory(Category category);

	CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	CategoryDTO updateCategory(Category category, Long categoryId);

	String deleteCategory(Long categoryId);
}
