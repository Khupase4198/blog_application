package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto caeateCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId); 
	
	void deleteCategory(int categoryId);
	
	CategoryDto getCategory(int categoryId);
	
	List<CategoryDto> getAllCategory();
	
}
