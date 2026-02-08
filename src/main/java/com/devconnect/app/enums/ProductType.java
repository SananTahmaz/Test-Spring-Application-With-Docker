package com.devconnect.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ProductType {
    NEW("New"),
    USED("Used");

    private final String label;
}
