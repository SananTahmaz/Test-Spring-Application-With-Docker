package com.devconnect.app.controllers;

import com.devconnect.app.dtos.category.CategoryCreateDto;
import com.devconnect.app.dtos.category.CategoryDto;
import com.devconnect.app.dtos.category.CategoryUpdateDto;
import com.devconnect.app.dtos.common.ApiResponse;
import com.devconnect.app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> create(@Valid @RequestBody CategoryCreateDto createDto) {
        CategoryDto categoryDto = categoryService.create(createDto);
        return new ResponseEntity<>(
                ApiResponse.success(categoryDto, "Category created successfully"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getById(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.getById(id);
        return new ResponseEntity<>(
                ApiResponse.success(categoryDto, "Category fetched successfully"),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAll() {
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        return new ResponseEntity<>(
                ApiResponse.success(categoryDtoList, "Category list fetched successfully"),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateDto updateDto) {
        CategoryDto categoryDto = categoryService.update(id, updateDto);
        return new ResponseEntity<>(
                ApiResponse.success(categoryDto, "Category updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(
                ApiResponse.success("Category deleted successfully"),
                HttpStatus.NO_CONTENT
        );
    }
}
