package com.algaworks.example.spring.cloud.stream.scheduler.infraestructure;

import com.algaworks.example.spring.cloud.stream.scheduler.api.model.HealthCheckTaskRequest;
import com.algaworks.example.spring.cloud.stream.scheduler.config.AppProperties;
import com.algaworks.example.spring.cloud.stream.scheduler.core.Mapper;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.service.ScheduleEventGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleEventGatewayWithStreamBridge implements ScheduleEventGateway {

    private final StreamBridge streamBridge;
    private final AppProperties appProperties;
    private final Mapper mapper;

    @Override
    public void sendExecuteTaskCommand(HealthCheckTask task) {
        HealthCheckTaskRequest request = mapper.map(task, HealthCheckTaskRequest.class);
        streamBridge.send(appProperties.getHealthCheckTaskChannel(), request);
    }
}
