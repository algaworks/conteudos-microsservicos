package com.example.springamqp.aula1.outbox;

import com.example.springamqp.aula1.core.JsonConverter;
import com.example.springamqp.aula1.core.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OutboxService {

    private final Logger log = LoggerFactory.getLogger(OutboxScheduler.class);

    @Autowired
    private OutboxMessageRepository outboxMessageRepository;

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private MessageSender messageSender;

    @Transactional
    public OutboxMessage store(String destination, Object content) {
        var json = jsonConverter.toJson(content);
        var outbox = new OutboxMessage(destination, json);
        return outboxMessageRepository.save(outbox);
    }

    @Transactional
    public void sendTopPending() {
        var pendingMessages = outboxMessageRepository.findFirst10ByStatusOrderByCreatedAtAsc(OutboxMessage.Status.PENDING);
        for (OutboxMessage message : pendingMessages) {
            message.increaseTentatives();
            try {
                messageSender.send(message.getDestination(), message.getContent());
            } catch (Exception e) {
                log.debug("Erro ao enviar mensagem da caixa de saida", e);
                if (message.getTentatives() >= 20) {
                    message.setStatus(OutboxMessage.Status.ERROR);
                }
                outboxMessageRepository.save(message);
                continue;
            }
            outboxMessageRepository.delete(message);
        }
    }
}
