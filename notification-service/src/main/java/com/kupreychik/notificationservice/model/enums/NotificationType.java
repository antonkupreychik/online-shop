package com.kupreychik.notificationservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {

    ORDER_CREATED("Ваш заказ создан. ID заказа - ");

    private String text;
}

