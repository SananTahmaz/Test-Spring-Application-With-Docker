package com.devconnect.app.mappers;

import com.devconnect.app.dtos.product.ProductCreateDto;
import com.devconnect.app.dtos.product.ProductDto;
import com.devconnect.app.dtos.product.ProductUpdateDto;
import com.devconnect.app.entities.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product toEntity(ProductCreateDto createDto) {
        return modelMapper.map(createDto, Product.class);
    }

    public void updateEntity(ProductUpdateDto updateDto, Product product) {
        modelMapper.map(updateDto, product);
    }
}
