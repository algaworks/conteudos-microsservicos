package com.algaworks.example.spring.cloud.stream.app.manager.domain.service;

import com.algaworks.example.spring.cloud.stream.app.manager.domain.model.App;

public interface AppEventGateway {
    void sendAppCreatedEvent(App app);
    void sendAppUpdatedEvent(App app);
}
