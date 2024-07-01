package com.kupreychik.statisticservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kupreychik.statisticservice.config.ProductServiceProperties;
import com.kupreychik.statisticservice.model.dto.DailyStatisticReport;
import com.kupreychik.statisticservice.model.dto.OrderItem;
import com.kupreychik.statisticservice.model.dto.ProductDto;
import com.kupreychik.statisticservice.model.dto.StatisticsDTO;
import com.kupreychik.statisticservice.model.entity.StatisticReport;
import com.kupreychik.statisticservice.repository.StatisticReportRepository;
import com.kupreychik.statisticservice.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticRepository statisticRepository;
    private final StatisticReportRepository statisticReportRepository;
    private final ObjectMapper objectMapper;
    private final ProductServiceProperties properties;

    public String getStatisticByDate(LocalDate date) {
        return statisticReportRepository.findFirstByTimestampGreaterThanOrderByTimestampDesc(date.atStartOfDay()).getData();
    }

    public void calculateDailyStatistics() {
        var data = calculateStatistic(LocalDate.now());
        List<DailyStatisticReport> dailyStatisticReportList = new ArrayList<>();
        for (Entry<ProductDto, Integer> productDtoIntegerEntry : data.entrySet()) {
            dailyStatisticReportList.add(new DailyStatisticReport(productDtoIntegerEntry.getKey(), productDtoIntegerEntry.getValue()));
        }

        StatisticReport statisticReport = new StatisticReport();
        statisticReport.setData(dailyStatisticReportList.toString());
        statisticReportRepository.save(statisticReport);
        log.info("Daily statistics calculated");
    }

    //25 04
    private Map<ProductDto, Integer> calculateStatistic(LocalDate dateOf) {
        var map = statisticRepository.findByTimestampBetween(dateOf.atStartOfDay(), dateOf.plusDays(1).atStartOfDay())
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
