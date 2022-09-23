package com.algaworks.example.spring.cloud.stream.checker.infraestructure;

import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.checker.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthChecker;
import com.algaworks.example.spring.cloud.stream.checker.domain.service.HealthResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
public class HttpHealthChecker implements HealthChecker {

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public HealthResult execute(HealthCheckTask task) {
        Validate.notNull(task);
        Validate.notNull(task.getApp());
        Validate.notNull(task.getCheckConfig());

        var checkConfig = task.getCheckConfig();
        var app = task.getApp();

        try {
            if (isReachable(app.getAddress(), checkConfig.getTimeout())) {
                log.info("Verificação via HTTP detectou o endereço " + app.getAddress() + " como online");
                return getOnlineResponse();
            } else {
                log.info("Verificação via HTTP detectou o endereço " + app.getAddress() + " como offline");
                return getOfflineResponse();
            }
        } catch (Exception e) {
            log.error("Falha ao consultar via HTTP o endereço " + app.getAddress(), e);
            return getUnknownResponse();
        }

    }

    private boolean isReachable(String address, Integer timeoutInSeconds) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .timeout(Duration.ofSeconds(timeoutInSeconds))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return httpResponse.statusCode() >= 200 && httpResponse.statusCode() <= 204;
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
