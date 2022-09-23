package com.algaworks.example.spring.cloud.stream.app.manager.api.controller;

import com.algaworks.example.spring.cloud.stream.app.manager.core.Mapper;
import com.algaworks.example.spring.cloud.stream.app.manager.api.model.AppUpdateRequest;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.model.App;
import com.algaworks.example.spring.cloud.stream.app.manager.api.model.AppCreateRequest;
import com.algaworks.example.spring.cloud.stream.app.manager.api.model.AppModel;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.repository.AppRepository;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.service.AppManagementService;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.service.exception.AppNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppManagementService appManagementService;
    private final AppRepository apps;
    private final Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppModel create(@RequestBody @Valid AppCreateRequest request) {
        App app = appManagementService.create(App.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build());
        return mapper.map(app, AppModel.class);
    }

    @PutMapping("/{appId}")
    public AppModel update(@PathVariable UUID appId,
                           @RequestBody @Valid AppUpdateRequest request) {
        App app = appManagementService.update(App.builder()
                .id(appId)
                .name(request.getName())
                .address(request.getAddress())
                .build());
        return mapper.map(app, AppModel.class);
    }

    @GetMapping("/{appId}")
    @Transactional
    public AppModel getById(@PathVariable UUID appId) {
        App app = apps.findById(appId).orElseThrow(AppNotFoundException::new);
        return mapper.map(app, AppModel.class);
    }

    @GetMapping
    @Transactional
    public List<AppModel> getAll() {
        return apps.findAll().stream().map(app -> mapper.map(app, AppModel.class)).toList();
    }

}

