package com.kupreychik.orderservice.service.impl;

import com.kupreychik.orderservice.config.ProductServiceProperties;
import com.kupreychik.orderservice.model.dto.OrderDto;
import com.kupreychik.orderservice.model.dto.OrderItemDto;
import com.kupreychik.orderservice.model.entity.Order;
import com.kupreychik.orderservice.model.entity.OrderItem;
import com.kupreychik.orderservice.repository.OrderItemRepository;
import com.kupreychik.orderservice.repository.OrderRepository;
import com.kupreychik.orderservice.service.OrderService;
import com.kupreychik.orderservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;


    @Override
    public Page<OrderDto> getOrdersByUserId(Long userId, Pageable pageable) {
        var orders = orderRepository.findAllByUserId(userId, pageable);
        if (orders.getContent().isEmpty()) {
            return Page.empty();
        }
        List<OrderDto> orderDtos = new ArrayList<>();

        orders.getContent().forEach(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setUserId(order.getUserId());
            orderDto.setStatus(order.getStatus());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setItems(new ArrayList<>());
            var orderItems = orderItemRepository.findAllByOrderId(order.getId());
            //TODO: rewrite for one request
            processOrderItems(orderItems, orderDto);

            orderDtos.add(orderDto);
        });

        return new PageImpl<>(orderDtos, pageable, orderDtos.size());
    }

    private void processOrderItems(List<OrderItem> orderItems, OrderDto orderDto) {
        orderItems.forEach(orderItem -> {
            var product = productService.findProductById(orderItem.getProductId());

            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setName(product.getName());
            orderItemDto.setPrice(orderItem.getPrice());
            orderItemDto.setQuantity(orderItem.getQuantity());

            orderDto.getItems().add(orderItemDto);
        });
    }

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order updateOrder(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(orderDetails.getStatus());
            order.setTotalPrice(orderDetails.getTotalPrice());
            return orderRepository.save(order);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}