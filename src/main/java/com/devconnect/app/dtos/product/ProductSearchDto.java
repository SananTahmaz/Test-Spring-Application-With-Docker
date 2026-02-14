package com.devconnect.app.dtos.product;

import com.devconnect.app.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductSearchDto {
    private String name;
    private BigDecimal minimumPrice;
    private BigDecimal maximumPrice;
    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;
    private Long categoryId;
    private List<ProductType> productTypeList;
}
