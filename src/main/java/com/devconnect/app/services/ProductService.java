package com.devconnect.app.services;

import com.devconnect.app.dtos.product.ProductCreateDto;
import com.devconnect.app.dtos.product.ProductDto;
import com.devconnect.app.dtos.product.ProductSearchDto;
import com.devconnect.app.dtos.product.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductCreateDto createDto);
    ProductDto createWithCategory(ProductCreateDto createDto, Long categoryId);
    void addToCategory(Long id, Long categoryId);
    void removeFromCategory(Long id);
    ProductDto getById(Long id);
    List<ProductDto> getAll();
    List<ProductDto> search(ProductSearchDto searchDto, Sort sort);
    Page<ProductDto> search(ProductSearchDto searchDto, Pageable pageable);
    ProductDto update(Long id, ProductUpdateDto updateDto);
    void delete(Long id);
}
