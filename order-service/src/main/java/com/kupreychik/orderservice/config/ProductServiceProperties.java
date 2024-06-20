package com.kupreychik.orderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.product-service")
public class ProductServiceProperties {
    private String url;
    private String path;
}
