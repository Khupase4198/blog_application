package com.blogapplication.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.entity.Category;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payloads.CategoryDto;
import com.blogapplication.repositories.CategoryRepo;
import com.blogapplication.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo repo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto caeateCategory(CategoryDto categoryDto) {
		Category category = dtoToCategory(categoryDto);
		Category savedUser = repo.save(category);
		return categoryToDto(savedUser);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category saveCategory = repo.save(category);
		CategoryDto updated = categoryToDto(saveCategory);
		return updated;
	}

	@Override
	public void deleteCategory(int categoryId) {
		repo.deleteById(categoryId);
		
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category category = repo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Categfory Id", categoryId));
		CategoryDto categoryDto = categoryToDto(category);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> listCategory = repo.findAll();
		List<CategoryDto> listCategoryDto = listCategory.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());
		return listCategoryDto;
	}

	public CategoryDto categoryToDto(Category category) {
		CategoryDto dto = modelMapper.map(category, CategoryDto.class);
		return dto;
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}
}
