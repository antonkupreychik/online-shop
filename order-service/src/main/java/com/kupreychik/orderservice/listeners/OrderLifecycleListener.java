package com.kupreychik.orderservice.listeners;

import com.kupreychik.orderservice.events.OrderItemAddedEvent;
import com.kupreychik.orderservice.repository.OrderItemRepository;
import com.kupreychik.orderservice.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderLifecycleListener {

    protected static final String STATISTIC = "statistic";

    private final KafkaService kafkaService;

    @EventListener
    public void stausUpdated(OrderItemAddedEvent entityEvent) {
        kafkaService.sendMessage(STATISTIC, entityEvent.getOrderItem().toString());
    }
}
