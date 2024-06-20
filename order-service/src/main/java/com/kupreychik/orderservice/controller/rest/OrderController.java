package com.kupreychik.orderservice.controller.rest;

import com.kupreychik.orderservice.model.command.OrderCommand;
import com.kupreychik.orderservice.model.dto.OrderDto;
import com.kupreychik.orderservice.model.entity.Order;
import com.kupreychik.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orders", description = "API for managing orders")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Creates a new order and returns it.")
    @ApiResponse(responseCode = "201", description = "Order created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))})
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCommand order) {
        OrderDto createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieves an order by its ID if it exists.")
    @ApiResponse(responseCode = "200", description = "Order found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))})
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order", description = "Updates an existing order by its ID.")
    @ApiResponse(responseCode = "200", description = "Order updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))})
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order", description = "Deletes an order by its ID.")
    @ApiResponse(responseCode = "200", description = "Order deleted")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves all orders in a paginated format.")
    @ApiResponse(responseCode = "200", description = "List of orders", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))})
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<OrderDto>> getOrdersByUserId(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId, pageable));
    }
}

