package com.kupreychik.orderservice.repository;

import com.kupreychik.orderservice.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    OrderStatus findByPreviousStatusIsNull();
}
