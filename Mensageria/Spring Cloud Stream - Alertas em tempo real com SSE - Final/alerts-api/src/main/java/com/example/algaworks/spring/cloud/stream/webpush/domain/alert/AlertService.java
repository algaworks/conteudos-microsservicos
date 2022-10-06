package com.example.algaworks.spring.cloud.stream.webpush.domain.alert;

import com.example.algaworks.spring.cloud.stream.webpush.api.FeedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class AlertService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter watchAlerts() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }

    public void addNewAlert(FeedEvent feedEvent) {
        emitters.forEach((emitter) -> {
            log.info("Eviando novo alerta " + feedEvent.getGuid());
            try {
                SseEmitter.SseEventBuilder builder = SseEmitter.event()
                        .id(feedEvent.getGuid())
                        .name("message")
                        .data(feedEvent);
                emitter.send(builder);
            } catch (ClientAbortException e) {
                log.warn("Cliente fechou a conexão");
                emitters.remove(emitter);
            } catch (Exception e) {
                log.error("Erro ao enviar uma mensagem via SSE " + feedEvent.getGuid(), e);
                emitters.remove(emitter);
            }
        });
    }

}
