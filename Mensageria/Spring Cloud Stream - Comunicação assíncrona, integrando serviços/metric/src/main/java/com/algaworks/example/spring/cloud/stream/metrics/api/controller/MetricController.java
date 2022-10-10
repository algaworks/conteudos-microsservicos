package com.algaworks.example.spring.cloud.stream.metrics.api.controller;

import com.algaworks.example.spring.cloud.stream.metrics.api.model.AppMetricModel;
import com.algaworks.example.spring.cloud.stream.metrics.core.Mapper;
import com.algaworks.example.spring.cloud.stream.metrics.domain.repository.AppMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/metrics")
@RequiredArgsConstructor
public class MetricController {
    private final AppMetricRepository metrics;
    private final Mapper mapper;

    @GetMapping
    public List<AppMetricModel> findAll() {
        return metrics.findAll()
                .stream()
                .map(metric -> mapper.map(metric, AppMetricModel.class))
                .toList();
    }

}
