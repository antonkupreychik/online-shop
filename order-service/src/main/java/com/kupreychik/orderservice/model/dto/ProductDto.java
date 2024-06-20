package com.kupreychik.orderservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantityInStock;
    private int categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

