package com.kupreychik.orderservice.service;


import com.kupreychik.orderservice.model.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    List<OrderStatus> getOrderStatuses();

    OrderStatus getFirstOrderStatus();

    OrderStatus getNextStatus(Long statusId);

    OrderStatus getPreviousStatus(Long statusId);
}
