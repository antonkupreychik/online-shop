package com.kupreychik.ratesservice.controller;

import com.kupreychik.ratesservice.model.entity.Rate;
import com.kupreychik.ratesservice.service.RatesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
@Tag(name = "RateController", description = "Контроллер для получения курсов валют")
public class RateController {

    private final RatesService ratesService;

    @Operation(summary = "Получить курсы валют по заданным валютам",
            description = "Возвращает список курсов валют для заданных исходной и целевой валюты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список курсов валют успешно получен"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<Rate>> getRateByCurrencyFromAndTo(
            @Parameter(description = "Дата", example = "2024-07-02")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate date,

            @Parameter(description = "Код целевой валюты", example = "USD")
            @RequestParam(defaultValue = "null") String toCurrency,

            @Parameter(description = "Код исходной валюты", example = "EUR")
            @RequestParam(defaultValue = "null") String fromCurrency) {
        toCurrency = processNull(toCurrency);
        fromCurrency = processNull(fromCurrency);

        return ResponseEntity.ok(ratesService.getRates(date, fromCurrency, toCurrency));
    }

    private String processNull(String currency) {
        if (currency.equals("null")) {
            currency = null;
        }
        return currency;
    }
}