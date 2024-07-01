package com.kupreychik.ratesservice.controller;

import com.kupreychik.ratesservice.model.entity.Currency;
import com.kupreychik.ratesservice.service.RatesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rate/currencies")
@RequiredArgsConstructor
@Tag(name = "CurrencyController", description = "Контроллер для получения доступных валют")
public class CurrencyController {

    private final RatesService ratesService;

    @Operation(summary = "Получить список доступных валют", description = "Возвращает список всех доступных валют")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список валют успешно получен"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies() {
        return ResponseEntity.ok(ratesService.getCurrencies());
    }
}