package com.kupreychik.statisticservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kupreychik.statisticservice.model.Statistic;
import com.kupreychik.statisticservice.model.dto.OrderItem;
import com.kupreychik.statisticservice.repository.StatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final StatRepository statisticsRepository;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = "statistic", groupId = "online-shop")
    public void listen(String message) {
        try {
            OrderItem orderItem = objectMapper.readValue(message, OrderItem.class);
            statisticsRepository.save(Statistic.builder()
                    .data(orderItem.toString())
                    .build());

            log.info("Received message: " + message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}