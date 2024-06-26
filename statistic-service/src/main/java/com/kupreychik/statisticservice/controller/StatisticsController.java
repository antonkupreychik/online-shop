package com.kupreychik.statisticservice.controller;

import com.kupreychik.statisticservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService service;

    @GetMapping
    public String findMostBuyingProduct(@RequestParam LocalDate dateOf) {
        return service.getStatisticByDate(dateOf);
    }
}
