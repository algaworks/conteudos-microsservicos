package com.example.springamqp.aula1.outbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxScheduler {

    @Autowired
    private OutboxService outboxService;

    @Scheduled(fixedRate = 30000L) //30 segundos
    public void sendPendingScheduler() {
        outboxService.sendTopPending();
    }

}
