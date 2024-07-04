package com.kupreychik.notificationservice.model.dto;

import com.kupreychik.notificationservice.model.enums.NotificationType;
import lombok.Data;

@Data
public class OrderCreatedDTO {
    private Long orderId;
    private Long userId;
    private NotificationType notificationType;
}