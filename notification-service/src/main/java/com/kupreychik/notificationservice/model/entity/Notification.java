package com.kupreychik.notificationservice.model.entity;

import com.kupreychik.notificationservice.model.enums.NotificationProvider;
import com.kupreychik.notificationservice.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notifications")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    private String id;
    private String data;
    private boolean success;
    private NotificationType type;
    private NotificationProvider provider;
}