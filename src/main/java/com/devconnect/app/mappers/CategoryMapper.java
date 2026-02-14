package com.devconnect.app.mappers;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;
import com.devconnect.app.entities.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category toEntity(CategoryCreateDto createDto) {
        return modelMapper.map(createDto, Category.class);
    }

    public void updateEntity(CategoryUpdateDto updateDto, Category category) {
        modelMapper.map(updateDto, category);
    }
}
