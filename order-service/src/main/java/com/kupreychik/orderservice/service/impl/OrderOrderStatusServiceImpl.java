package com.kupreychik.orderservice.service.impl;

import com.kupreychik.orderservice.model.entity.OrderStatus;
import com.kupreychik.orderservice.repository.OrderStatusRepository;
import com.kupreychik.orderservice.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderOrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @Override
    @Cacheable(value = "order_statuses")
    public List<OrderStatus> getOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus getFirstOrderStatus() {
        return orderStatusRepository.findByPreviousStatusIsNull();
    }

    @Override
    public OrderStatus getNextStatus(Long statusId) {
        return orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Status with id " + statusId + " not found"))
                .getNextStatus();
    }

    @Override
    public OrderStatus getPreviousStatus(Long statusId) {
        return orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Status with id " + statusId + " not found"))
                .getPreviousStatus();
    }
}
