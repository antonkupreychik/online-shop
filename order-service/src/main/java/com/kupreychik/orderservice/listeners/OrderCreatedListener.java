package com.kupreychik.orderservice.listeners;

import com.kupreychik.orderservice.events.OrderCreatedEvent;
import com.kupreychik.orderservice.model.dto.OrderCreatedDTO;
import com.kupreychik.orderservice.service.KafkaService;
import com.kupreychik.orderservice.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {

    protected static final String ORDER_CREATED = "ORDER_CREATED";

    private final KafkaService kafkaService;

    @EventListener
    public void stausUpdated(OrderCreatedEvent entityEvent) {
        OrderCreatedDTO orderCreatedDTO = new OrderCreatedDTO();
        orderCreatedDTO.setOrderId(entityEvent.getOrder().getId());
        orderCreatedDTO.setNotificationType(ORDER_CREATED);
        orderCreatedDTO.setUserId(entityEvent.getOrder().getUserId());

        kafkaService.sendMessage("order-created", orderCreatedDTO.toString());

    }
}
