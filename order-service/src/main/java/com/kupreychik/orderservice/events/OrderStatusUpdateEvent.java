package com.kupreychik.orderservice.events;

import com.kupreychik.orderservice.model.entity.OrderStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderStatusUpdateEvent extends ApplicationEvent {

    private final OrderStatus orderStatus;

    public OrderStatusUpdateEvent(Object entity, OrderStatus orderStatus) {
        super(entity);
        this.orderStatus = orderStatus;
    }
}
