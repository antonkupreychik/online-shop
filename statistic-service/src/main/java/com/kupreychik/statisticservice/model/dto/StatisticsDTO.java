package com.kupreychik.statisticservice.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatisticsDTO {
    private Long productId;
    private Long totalCount;
    private LocalDateTime timestamp;

    public StatisticsDTO(Long productId, Long totalCount, LocalDateTime timestamp) {
        this.productId = productId;
        this.totalCount = totalCount;
        this.timestamp = timestamp;
    }
}
