package com.devconnect.app.services.implementations;

import com.devconnect.app.dtos.product.ProductCreateDto;
import com.devconnect.app.dtos.product.ProductDto;
import com.devconnect.app.dtos.product.ProductSearchDto;
import com.devconnect.app.dtos.product.ProductUpdateDto;
import com.devconnect.app.entities.Category;
import com.devconnect.app.entities.Product;
import com.devconnect.app.exceptions.NotFoundException;
import com.devconnect.app.mappers.ProductMapper;
import com.devconnect.app.repositories.CategoryRepository;
import com.devconnect.app.repositories.ProductRepository;
import com.devconnect.app.services.ProductService;
import com.devconnect.app.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductDto create(ProductCreateDto createDto) {
        Product product = productMapper.toEntity(createDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductDto createWithCategory(ProductCreateDto createDto, Long categoryId) {
        Product product = productMapper.toEntity(createDto);
        if (categoryId != null) {
            Category category = categoryRepository
                    .findById(categoryId)
                    .orElseThrow(
                            () -> new NotFoundException(String.format("Category not found with id: %d", categoryId))
                    );
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Override
    @Transactional
    public void addToCategory(Long id, Long categoryId) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with id: %d", id)));

        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id: %d", categoryId)));
        product.setCategory(category);
    }

    @Override
    @Transactional
    public void removeFromCategory(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with id: %d", id)));
        product.setCategory(null);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with id: %d", id)));
        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> search(ProductSearchDto searchDto, Sort sort) {
        Sort defaultSort = sort.isUnsorted()
                ? Sort.by(Sort.Direction.DESC, "createdAt")
                : sort;

        Specification<Product> productSpecification = ProductSpecification.build(searchDto);
        return productRepository
                .findAll(productSpecification, defaultSort)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> search(ProductSearchDto searchDto, Pageable pageable) {
        Specification<Product> productSpecification = ProductSpecification.build(searchDto);
        return productRepository
                .findAll(productSpecification, pageable)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductUpdateDto updateDto) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with id: %d", id)));

        productMapper.updateEntity(updateDto, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found with id: %d", id)));
        productRepository.delete(product);
    }
}
