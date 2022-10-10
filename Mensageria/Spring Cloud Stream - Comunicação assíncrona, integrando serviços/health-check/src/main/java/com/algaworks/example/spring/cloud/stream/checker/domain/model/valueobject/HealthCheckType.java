package com.algaworks.example.spring.cloud.stream.checker.domain.model.valueobject;

import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthChecker;
import com.algaworks.example.spring.cloud.stream.checker.infraestructure.HttpHealthChecker;
import com.algaworks.example.spring.cloud.stream.checker.infraestructure.PingHealthChecker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HealthCheckType {
    HTTP(new HttpHealthChecker()),
    PING(new PingHealthChecker());

    private final HealthChecker healthChecker;

    public HealthChecker getHealthChecker() {
        return healthChecker;
    }
}
