package com.blog.blog_apis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_apis.entities.Category;
import com.blog.blog_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_apis.payloads.CategoryDto;
import com.blog.blog_apis.repositories.CategoryRepo;
import com.blog.blog_apis.services.CategoryServices;

@Service
public class CategoryServiceImpl implements CategoryServices{
    

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepo.save(category);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).
        orElseThrow(()->new ResourceNotFoundException("CAtegory", "CategoryId", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        CategoryDto categoryDto2 = this.modelMapper.map(updatedCategory, CategoryDto.class);

        return categoryDto2;

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
        .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
       Category category = this.categoryRepo.findById(categoryId)
       .orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
       return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categorys = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categorys.stream().map((category)-> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    
}


