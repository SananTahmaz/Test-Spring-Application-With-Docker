package com.devconnect.app.specifications;

import com.devconnect.app.dtos.category.CategorySearchDto;
import com.devconnect.app.entities.Category;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CategorySpecification {
    public static Specification<Category> build(CategorySearchDto searchDto) {
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
            return cb.and(predicateList.toArray(new Predicate[0]));
        });
    }
}
