package com.example.springamqp.aula1.infra.rabbitmq;

import com.example.springamqp.aula1.core.MessageSender;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderWithRabbitMQ implements MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String destination, String rawContent) {
        rabbitTemplate.send(destination, "", MessageBuilder.withBody(rawContent.getBytes()).build());
    }
}
