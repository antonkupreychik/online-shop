package com.kupreychik.orderservice.mapper;

import com.kupreychik.orderservice.model.dto.OrderItemDto;
import com.kupreychik.orderservice.model.entity.OrderItem;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "name", expression="java(productName)")
    OrderItemDto toDto(OrderItem orderItem, @Context String productName);
}
