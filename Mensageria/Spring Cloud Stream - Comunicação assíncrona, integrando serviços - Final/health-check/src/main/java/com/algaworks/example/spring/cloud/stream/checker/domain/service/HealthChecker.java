package com.algaworks.example.spring.cloud.stream.checker.domain.service;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTask;

public interface HealthChecker {
    HealthResult execute(HealthCheckTask task);
}
