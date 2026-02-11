package com.devconnect.app.services.implementations;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;
import com.devconnect.app.entities.Category;
import com.devconnect.app.exceptions.AlreadyExistsException;
import com.devconnect.app.exceptions.NotFoundException;
import com.devconnect.app.mappers.CategoryMapper;
import com.devconnect.app.repositories.CategoryRepository;
import com.devconnect.app.repositories.ProductRepository;
import com.devconnect.app.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(
            CategoryMapper categoryMapper,
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public CategoryDto getById(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: %d", id)));
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: %d", id)));

        categoryRepository.clearCategoryFromProducts(id);
        categoryRepository.delete(category);
    }
}
