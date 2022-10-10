package com.algaworks.example.spring.cloud.stream.scheduler.api.listener;

import com.algaworks.example.spring.cloud.stream.scheduler.api.model.AppModel;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.App;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppCreatedEventListener implements Consumer<AppModel> {

    private final AppRepository apps;

    @Override
    public void accept(AppModel appModel) {
        log.info("App criado recebido " + appModel.getId());
        App app = App.builder()
                .id(appModel.getId())
                .name(appModel.getName())
                .address(appModel.getAddress())
                .build();
        apps.saveAndFlush(app);
    }
}
