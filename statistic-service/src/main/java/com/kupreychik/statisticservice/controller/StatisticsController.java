package com.kupreychik.statisticservice.controller;

import com.kupreychik.statisticservice.model.dto.ProductDto;
import com.kupreychik.statisticservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService service;

    @GetMapping
    public Map<ProductDto, Integer> findMostBuyingProduct() {
        return service.getStatistics();
    }
}
