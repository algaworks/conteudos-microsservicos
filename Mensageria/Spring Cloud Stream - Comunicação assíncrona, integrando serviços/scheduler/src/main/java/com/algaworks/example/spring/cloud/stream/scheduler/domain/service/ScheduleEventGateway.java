package com.algaworks.example.spring.cloud.stream.scheduler.domain.service;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.HealthCheckTask;

public interface ScheduleEventGateway {
    void sendExecuteTask(HealthCheckTask task);
}
