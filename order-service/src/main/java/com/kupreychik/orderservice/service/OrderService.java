package com.kupreychik.orderservice.service;

import com.kupreychik.orderservice.model.command.OrderCommand;
import com.kupreychik.orderservice.model.dto.OrderDto;
import com.kupreychik.orderservice.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> getAllOrders(Pageable pageable);

    boolean deleteOrder(Long id);

    Order updateOrder(Long id, Order orderDetails);

    Order getOrderById(Long id);

    OrderDto createOrder(OrderCommand order);

    Page<OrderDto> getOrdersByUserId(Long userId, Pageable pageable);
}
