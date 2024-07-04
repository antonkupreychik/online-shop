package com.kupreychik.notificationservice.model.entity;

import com.kupreychik.notificationservice.model.enums.NotificationProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users_data")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    private String id;
    private Long userId;
    private String data;
    private NotificationProvider provider;
}
