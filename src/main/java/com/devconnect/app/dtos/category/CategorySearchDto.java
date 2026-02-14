package com.devconnect.app.dtos.category;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategorySearchDto {
    private String name;
    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;
}
