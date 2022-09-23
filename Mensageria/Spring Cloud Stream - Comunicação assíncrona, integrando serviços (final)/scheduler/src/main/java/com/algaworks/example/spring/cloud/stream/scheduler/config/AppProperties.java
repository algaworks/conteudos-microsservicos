package com.algaworks.example.spring.cloud.stream.scheduler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties
@Component
public class AppProperties {
    private String healthCheckTaskChannel = "healthCheckTaskCommand-in-0";
}
