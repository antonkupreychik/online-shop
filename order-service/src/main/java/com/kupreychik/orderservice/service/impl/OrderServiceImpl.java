package com.kupreychik.orderservice.service.impl;

import com.kupreychik.orderservice.events.OrderCreatedEvent;
import com.kupreychik.orderservice.mapper.OrderItemMapper;
import com.kupreychik.orderservice.mapper.OrderMapper;
import com.kupreychik.orderservice.model.command.OrderCommand;
import com.kupreychik.orderservice.model.dto.OrderDto;
import com.kupreychik.orderservice.model.entity.Order;
import com.kupreychik.orderservice.model.entity.OrderItem;
import com.kupreychik.orderservice.model.entity.OrderStatus;
import com.kupreychik.orderservice.repository.OrderItemRepository;
import com.kupreychik.orderservice.repository.OrderRepository;
import com.kupreychik.orderservice.service.OrderService;
import com.kupreychik.orderservice.service.OrderStatusService;
import com.kupreychik.orderservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderStatusService orderStatusService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public Page<OrderDto> getOrdersByUserId(Long userId, Pageable pageable) {
        var orders = orderRepository.findAllByUserId(userId, pageable);
        if (orders.getContent().isEmpty()) {
            return Page.empty();
        }
        var orderDtos = new ArrayList<OrderDto>();

        orders.getContent().forEach(order -> {
            var orderDto = orderMapper.toDto(order);
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
            orderDto.getItems()
                    .add(
                            orderItemMapper.toDto(orderItem, product.getName())
                    );
        });
    }

    @Transactional
    public OrderDto createOrder(OrderCommand orderCommand) {
        var orderStatus = orderStatusService.getFirstOrderStatus();
        var order = orderMapper.toOrder(orderCommand);
        order.setStatus(orderStatus);
        order = orderRepository.save(order);

        publisher.publishEvent(createOrderStatusUpdateEvent(order));
        return orderMapper.toDto(order);
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

    private OrderCreatedEvent createOrderStatusUpdateEvent(Order order) {
        return new OrderCreatedEvent(order);
    }
}