package com.kupreychik.orderservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
