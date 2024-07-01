package com.kupreychik.ratesservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.rates")
public class BelarusBankApiProperties {
    private String url;
    private String path;
}
