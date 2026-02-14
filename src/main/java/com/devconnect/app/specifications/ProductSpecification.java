package com.devconnect.app.specifications;

import com.devconnect.app.dtos.product.ProductSearchDto;
import com.devconnect.app.entities.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> build(ProductSearchDto searchDto) {
        return ((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchDto.getName() != null && !searchDto.getName().isBlank()) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + searchDto.getName().toLowerCase() + "%"
                        )
                );
            }
            if (searchDto.getMinimumPrice() != null) {
                predicateList.add(
                        cb.greaterThanOrEqualTo(root.get("price"), searchDto.getMinimumPrice())
                );
            }
            if (searchDto.getMaximumPrice() != null) {
                predicateList.add(
                        cb.lessThanOrEqualTo(root.get("price"), searchDto.getMaximumPrice())
                );
            }
            if (searchDto.getCreatedAfter() != null) {
                predicateList.add(
                        cb.greaterThanOrEqualTo(root.get("createdAt"), searchDto.getCreatedAfter())
                );
            }
            if (searchDto.getCreatedBefore() != null) {
                predicateList.add(
                        cb.lessThanOrEqualTo(root.get("createdAt"), searchDto.getCreatedBefore())
                );
            }
            if (searchDto.getCategoryId() != null) {
                predicateList.add(
                        cb.equal(root.get("category").get("id"), searchDto.getCategoryId())
                );
            }
            if (searchDto.getProductTypeList() != null && !searchDto.getProductTypeList().isEmpty()) {
                predicateList.add(
                        root.get("productType").in(searchDto.getProductTypeList())
                );
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        });
    }
}
