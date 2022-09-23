package com.algaworks.example.spring.cloud.stream.app.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class AppProperties {
    private String appCreatedBinding = "appCreatedSupplier-out-0";
    private String appUpdatedBinding = "appUpdatedSupplier-out-0";
}
