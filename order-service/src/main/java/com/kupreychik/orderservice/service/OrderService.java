package com.kupreychik.orderservice.service;

import com.kupreychik.orderservice.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> getAllOrders(Pageable pageable);

    boolean deleteOrder(Long id);

    Order updateOrder(Long id, Order orderDetails);

    Order getOrderById(Long id);

    Order createOrder(Order order);
}
