package com.devconnect.app.services;

import com.devconnect.app.dtos.product.ProductCreateDto;
import com.devconnect.app.dtos.product.ProductDto;
import com.devconnect.app.dtos.product.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductCreateDto createDto);
    ProductDto getById(Long id);
    List<ProductDto> getAll();
    ProductDto update(Long id, ProductUpdateDto updateDto);
    void delete(Long id);
}
