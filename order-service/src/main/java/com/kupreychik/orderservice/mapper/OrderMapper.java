package com.kupreychik.orderservice.mapper;

import com.kupreychik.orderservice.model.command.OrderCommand;
import com.kupreychik.orderservice.model.dto.OrderDto;
import com.kupreychik.orderservice.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "totalPrice", expression = "java(new java.math.BigDecimal(0))")
    Order toOrder(OrderCommand orderCommand);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "status", source = "status.name")
    OrderDto toDto(Order save);
}
