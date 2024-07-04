package com.kupreychik.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kupreychik.notificationservice.model.dto.OrderCreatedDTO;
import com.kupreychik.notificationservice.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderInfoConsumer {

    private final ObjectMapper objectMapper;
    private final TelegramService telegramService;

    @KafkaListener(topics = "order-created", groupId = "online-shop")
    public void listen(String message) {
        try {
            OrderCreatedDTO orderCreatedDTO = objectMapper.readValue(message, OrderCreatedDTO.class);
            telegramService.sendMessage(orderCreatedDTO.getUserId(), orderCreatedDTO.getNotificationType().getText() + orderCreatedDTO.getOrderId());
            log.info("Received message: " + message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}