package com.devconnect.app.controllers;

import com.devconnect.app.dtos.common.ApiResponse;
import com.devconnect.app.dtos.product.ProductCreateDto;
import com.devconnect.app.dtos.product.ProductDto;
import com.devconnect.app.dtos.product.ProductUpdateDto;
import com.devconnect.app.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> create(@Valid @RequestBody ProductCreateDto createDto) {
        ProductDto productDto = productService.create(createDto);
        return new ResponseEntity<>(
                ApiResponse.success(productDto, "Product created successfully"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getById(@PathVariable Long id) {
        ProductDto productDto = productService.getById(id);
        return new ResponseEntity<>(
                ApiResponse.success(productDto, "Product fetched successfully"),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAll() {
        List<ProductDto> productDtoList = productService.getAll();
        return new ResponseEntity<>(
                ApiResponse.success(productDtoList, "Product list fetched successfully"),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDto updateDto) {
        ProductDto productDto = productService.update(id, updateDto);
        return new ResponseEntity<>(
                ApiResponse.success(productDto, "Product updated successfully"),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(
                ApiResponse.success("Product created successfully"),
                HttpStatus.NO_CONTENT
        );
    }
}
