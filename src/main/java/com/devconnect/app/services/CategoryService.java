package com.devconnect.app.services;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryCreateDto createDto);
    CategoryDto getById(Long id);
    List<CategoryDto> getAll();
    CategoryDto update(Long id, CategoryUpdateDto updateDto);
    void delete(Long id);
}
