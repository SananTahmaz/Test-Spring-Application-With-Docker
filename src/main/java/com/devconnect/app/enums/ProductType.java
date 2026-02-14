package com.devconnect.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum ProductType {
    NEW("New"),
    USED("Used");

    private final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static ProductType fromValue(String value) {
        for (ProductType productType : ProductType.values()) {
            if (productType.name().equalsIgnoreCase(value) || productType.label.equalsIgnoreCase(value)) {
                return productType;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid product type: %s", value));
    }
}
