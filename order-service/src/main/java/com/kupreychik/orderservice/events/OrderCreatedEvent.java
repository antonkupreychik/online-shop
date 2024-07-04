package com.kupreychik.orderservice.events;

import com.kupreychik.orderservice.model.entity.Order;
import com.kupreychik.orderservice.model.entity.OrderStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final Order order;

    public OrderCreatedEvent(Object entity) {
        super(entity);
        this.order = (Order) entity;
    }
}
