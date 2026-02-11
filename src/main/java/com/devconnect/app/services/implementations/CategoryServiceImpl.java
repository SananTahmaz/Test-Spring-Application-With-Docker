package com.devconnect.app.services.implementations;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;
import com.devconnect.app.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDto create(CategoryCreateDto createDto) {
        return null;
    }

    @Override
    public CategoryDto getById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDto> getAll() {
        return List.of();
    }

    @Override
    public CategoryDto update(Long id, CategoryUpdateDto updateDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
