package com.devconnect.app.dtos.product;

import com.devconnect.app.annotations.NotNegative;
import com.devconnect.app.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNegative
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
    private ProductType productType;
}
