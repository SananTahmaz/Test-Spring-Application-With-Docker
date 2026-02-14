package com.devconnect.app.services;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategorySearchDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryCreateDto createDto);
    CategoryDto getById(Long id);
    List<CategoryDto> getAll();
    List<CategoryDto> search(CategorySearchDto searchDto, Sort sort);
    Page<CategoryDto> search(CategorySearchDto searchDto, Pageable pageable);
    CategoryDto update(Long id, CategoryUpdateDto updateDto);
    void delete(Long id);
}
