package com.kupreychik.orderservice.service;

import com.kupreychik.orderservice.model.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItem orderItem);

    OrderItem getOrderItemById(Long orderItemId);

    OrderItem updateOrderItem(Long orderItemId, OrderItem orderItemDetails);

    boolean deleteOrderItem(Long orderItemId);

    List<OrderItem> getOrderItemsByOrderId(Long orderId);
}
