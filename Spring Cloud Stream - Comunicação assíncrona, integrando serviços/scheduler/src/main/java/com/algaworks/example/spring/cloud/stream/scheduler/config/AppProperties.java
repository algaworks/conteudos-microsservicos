package com.algaworks.example.spring.cloud.stream.scheduler.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProperties {
    private String healthCheckTaskChannel = "healthCheckTaskCommand-out-0";
}
