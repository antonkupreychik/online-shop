package com.kupreychik.orderservice.listeners;

import com.kupreychik.orderservice.events.OrderCreatedEvent;
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
    private final OrderItemRepository orderItemRepository;


    @EventListener
    public void stausUpdated(OrderCreatedEvent entityEvent) {
        var orderId = entityEvent.getOrderItem().getOrderId();
        var orderItems = orderItemRepository.findAllByOrderId(orderId);

        kafkaService.sendMessage(STATISTIC, entityEvent.getOrderItem().toString());
    }
}
