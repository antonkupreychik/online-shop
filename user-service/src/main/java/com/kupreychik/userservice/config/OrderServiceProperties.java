package com.kupreychik.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.order-service")
public class OrderServiceProperties {
    private String url;
    private String path;
}

