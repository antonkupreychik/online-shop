package com.kupreychik.schedulerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendCalculateStatisticsEvent() {
        kafkaTemplate.send("SCHEDULED_STATISTICS", "START_CALCULATE");
        log.info("Message sent to topic SCHEDULED_STATISTICS : START_CALCULATE");
    }
}
