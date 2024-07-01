package com.kupreychik.orderservice.events;

import com.kupreychik.orderservice.model.entity.OrderItem;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final OrderItem orderItem;

    public OrderCreatedEvent(Object entity) {
        super(entity);
        this.orderItem = (OrderItem) entity;
    }
}
