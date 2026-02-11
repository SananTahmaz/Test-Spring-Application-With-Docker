package com.devconnect.app.services;

import com.devconnect.app.dtos.product.ProductCreateDto;
import com.devconnect.app.dtos.product.ProductDto;
import com.devconnect.app.dtos.product.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductCreateDto createDto);
    ProductDto createWithCategory(ProductCreateDto createDto, Long categoryId);
    void addToCategory(Long id, Long categoryId);
    void removeFromCategory(Long id);
    ProductDto getById(Long id);
    List<ProductDto> getAll();
    ProductDto update(Long id, ProductUpdateDto updateDto);
    void delete(Long id);
}
