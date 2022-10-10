package com.algaworks.example.spring.cloud.stream.checker.domain.service;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTaskResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthResult {
    private HealthCheckTaskResult.Status status;
    private Integer responseTime;
}
