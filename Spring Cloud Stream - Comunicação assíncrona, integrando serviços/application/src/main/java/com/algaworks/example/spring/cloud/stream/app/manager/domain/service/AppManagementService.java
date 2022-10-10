package com.algaworks.example.spring.cloud.stream.app.manager.domain.service;

import com.algaworks.example.spring.cloud.stream.app.manager.domain.model.App;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.repository.AppRepository;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.service.exception.AppNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppManagementService {

    private final AppRepository apps;
    private final AppEventGateway appEventGateway;

    @Transactional
    public App create(App app) {
        Validate.notNull(app);
        apps.saveAndFlush(app);
        appEventGateway.sendAppCreatedEvent(app);

        return app;
    }

    @Transactional
    public App update(App updatedApp) {
        Validate.notNull(updatedApp);

        App existingApp = findAppById(updatedApp.getId());

        existingApp.update(updatedApp);
        apps.saveAndFlush(existingApp);
        appEventGateway.sendAppUpdatedEvent(existingApp);

        return existingApp;
    }

    private App findAppById(UUID appId) {
        return apps.findById(appId).orElseThrow(AppNotFoundException::new);
    }

}
