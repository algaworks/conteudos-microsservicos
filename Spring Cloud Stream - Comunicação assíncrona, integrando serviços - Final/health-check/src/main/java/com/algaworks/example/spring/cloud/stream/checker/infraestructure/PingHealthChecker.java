package com.algaworks.example.spring.cloud.stream.checker.infraestructure;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthChecker;
import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.net.InetAddress;

@Slf4j
public class PingHealthChecker implements HealthChecker {
    
    @Override
    public HealthResult execute(HealthCheckTask task) {
        Validate.notNull(task);
        Validate.notNull(task.getApp());
        Validate.notNull(task.getCheckConfig());

        var checkConfig = task.getCheckConfig();
        var app = task.getApp();

        //timer
        try {
            if (isReachable(app.getAddress(), checkConfig.getTimeout())) {
                log.info("Verificação via PING detectou o endereço " + app.getAddress() + " como online");
                return getOnlineResponse();
            } else {
                log.info("Verificação via PING detectou o endereço " + app.getAddress() + " como offline");
                return getOfflineResponse();
            }
        } catch (Exception e) {
            log.error("Falha ao consultar via PING o endereço " + app.getAddress(), e);
            return getUnknownResponse();
        }

    }

    private boolean isReachable(String address, Integer timeout) throws IOException {
        return InetAddress.getByName(address).isReachable(timeout);
    }

    private HealthResult getOnlineResponse() {
        return HealthResult.builder()
                .responseTime(10)
                .status(HealthCheckTaskResult.Status.UP)
                .build();
    }

    private HealthResult getOfflineResponse() {
        return HealthResult.builder()
                .responseTime(10)
                .status(HealthCheckTaskResult.Status.DOWN)
                .build();
    }

    private static HealthResult getUnknownResponse() {
        return HealthResult.builder()
                .responseTime(10)
                .status(HealthCheckTaskResult.Status.UNKNOWN)
                .build();
    }
}
