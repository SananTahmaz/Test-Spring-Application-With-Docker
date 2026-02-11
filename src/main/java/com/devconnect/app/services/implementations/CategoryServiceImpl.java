package com.devconnect.app.services.implementations;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;
import com.devconnect.app.entities.Category;
import com.devconnect.app.exceptions.AlreadyExistsException;
import com.devconnect.app.exceptions.NotFoundException;
import com.devconnect.app.mappers.CategoryMapper;
import com.devconnect.app.repositories.CategoryRepository;
import com.devconnect.app.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto create(CategoryCreateDto createDto) {
        Optional<Category> foundCategory = categoryRepository.findByName(createDto.getName());
        if (foundCategory.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("Category already exists with name: %s", createDto.getName())
            );
        }

        Category category = categoryMapper.toEntity(createDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: %d", id)));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto update(Long id, CategoryUpdateDto updateDto) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: %d", id)));

        Optional<Category> foundCategory = categoryRepository.findByName(updateDto.getName());
        if (foundCategory.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("Category already exists with name: %s", updateDto.getName())
            );
        }

        categoryMapper.updateEntity(updateDto, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: %d", id)));
        categoryRepository.delete(category);
    }
}
