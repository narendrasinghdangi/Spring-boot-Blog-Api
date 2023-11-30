package com.blog.blog_apis.services;

import java.util.List;

import com.blog.blog_apis.payloads.CategoryDto;

public interface CategoryServices {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDto getCategoryById(Integer categoryId);
    List<CategoryDto> getAllCategory();
}
