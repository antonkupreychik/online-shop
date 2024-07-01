package com.kupreychik.orderservice.controller.rest;

import com.kupreychik.orderservice.model.entity.OrderItem;
import com.kupreychik.orderservice.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
@RequiredArgsConstructor
@Tag(name = "Order Items", description = "API for managing order items within an order")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    @Operation(summary = "Add an order item", description = "Adds a new item to an order.")
    @ApiResponse(responseCode = "201", description = "Order item created successfully", content = @Content(schema = @Schema(implementation = OrderItem.class)))
    public ResponseEntity<OrderItem> addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem orderItem) {
        orderItem.setOrderId(orderId); // Ensure the order item is linked to the correct order
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    @GetMapping("/{orderItemId}")
    @Operation(summary = "Get order item by ID", description = "Retrieves a specific order item by its ID.")
    @ApiResponse(responseCode = "200", description = "Order item found", content = @Content(schema = @Schema(implementation = OrderItem.class)))
    @ApiResponse(responseCode = "404", description = "Order item not found")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);
        if (orderItem != null) {
            return ResponseEntity.ok(orderItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{orderItemId}")
    @Operation(summary = "Update order item", description = "Updates an existing order item.")
    @ApiResponse(responseCode = "200", description = "Order item updated successfully", content = @Content(schema = @Schema(implementation = OrderItem.class)))
    @ApiResponse(responseCode = "404", description = "Order item not found")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId, @RequestBody OrderItem orderItemDetails) {
        orderItemDetails.setOrderId(orderId); // Ensure the order item remains linked to the correct order
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemDetails);
        if (updatedOrderItem != null) {
            return ResponseEntity.ok(updatedOrderItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderItemId}")
    @Operation(summary = "Delete order item", description = "Deletes an order item by its ID.")
    @ApiResponse(responseCode = "200", description = "Order item deleted")
    @ApiResponse(responseCode = "404", description = "Order item not found")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        boolean isDeleted = orderItemService.deleteOrderItem(orderItemId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Get all order items by order ID", description = "Retrieves all items for a given order.")
    @ApiResponse(responseCode = "200", description = "Order items retrieved successfully", content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<OrderItem>> getAllOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }
}
