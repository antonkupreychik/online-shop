package com.kupreychik.statisticservice.model.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("description", description)
                .append("price", price)
                .append("quantityInStock", quantityInStock)
                .append("categoryId", categoryId)
                .append("createdAt", createdAt)
                .append("updatedAt", updatedAt)
                .toString();
    }
}

