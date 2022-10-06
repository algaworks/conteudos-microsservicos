package com.example.algaworks.spring.cloud.stream.webpush.api;

import com.example.algaworks.spring.cloud.stream.webpush.domain.alert.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class FeedEventConsumer implements Consumer<FeedEvent> {

    private final AlertService alertService;

    @Override
    public void accept(FeedEvent feedEvent) {
        alertService.addNewAlert(feedEvent);
    }
}
