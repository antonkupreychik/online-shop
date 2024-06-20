package com.kupreychik.orderservice.service.impl;

import com.kupreychik.orderservice.model.entity.OrderItem;
import com.kupreychik.orderservice.repository.OrderItemRepository;
import com.kupreychik.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional(readOnly = true)
    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }

    @Transactional
    public OrderItem updateOrderItem(Long orderItemId, OrderItem orderItemDetails) {
        return orderItemRepository.findById(orderItemId).map(existingItem -> {
            existingItem.setProductId(orderItemDetails.getProductId());
            existingItem.setQuantity(orderItemDetails.getQuantity());
            existingItem.setPrice(orderItemDetails.getPrice());
            return orderItemRepository.save(existingItem);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteOrderItem(Long orderItemId) {
        if (orderItemRepository.existsById(orderItemId)) {
            orderItemRepository.deleteById(orderItemId);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }
}
