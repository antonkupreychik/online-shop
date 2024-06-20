package com.kupreychik.orderservice.listeners;

import com.kupreychik.orderservice.events.OrderStatusUpdateEvent;
import com.kupreychik.orderservice.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusUpdateListener {

    private final OrderStatusService orderStatusService;

    @EventListener
    public void stausUpdated(OrderStatusUpdateEvent entityEvent) {
        var statuses = orderStatusService.getOrderStatuses();

        if (entityEvent.getOrderStatus().getName().equals("New")) {
            System.out.println("Order status updated");
        }

    }
}
