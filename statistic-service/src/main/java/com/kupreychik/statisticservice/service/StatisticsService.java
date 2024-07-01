package com.kupreychik.statisticservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kupreychik.statisticservice.config.ProductServiceProperties;
import com.kupreychik.statisticservice.model.dto.OrderItem;
import com.kupreychik.statisticservice.model.dto.ProductDto;
import com.kupreychik.statisticservice.model.dto.StatisticsDTO;
import com.kupreychik.statisticservice.repository.StatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatRepository statRepository;
    private final ObjectMapper objectMapper;
    private final ProductServiceProperties properties;


    public Map<ProductDto, Integer> getStatistics() {
        var map = statRepository.findAll()
                .stream()
                .map(el -> {
                    try {
                        OrderItem orderItem = objectMapper.readValue(el.getData(), OrderItem.class);
                        return new StatisticsDTO(orderItem.getProductId(), orderItem.getQuantity(), el.getTimestamp());
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.groupingBy(
                        StatisticsDTO::getProductId,
                        Collectors.summingInt(el -> Math.toIntExact(el.getTotalCount()))
                ));

        Map<ProductDto, Integer> result = new HashMap<>();
        for (Entry<Long, Integer> longIntegerEntry : map.entrySet()) {
            var stringItemName = findProductById(longIntegerEntry.getKey());
            result.put(stringItemName, longIntegerEntry.getValue());
        }
        return result;
    }

    private ProductDto findProductById(Long id) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(properties.getUrl() + properties.getPath() + "/" + id)
                .retrieve()
                .body(ProductDto.class);
    }
}
