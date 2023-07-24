package com.example.springamqp.aula1.order;

import com.example.springamqp.aula1.outbox.OutboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRegistrationService {

    @Autowired
    private OrderRepository orders;

    @Autowired
    private OutboxService outboxService;

    public Order register(Order order) {
        orders.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getValue());
        outboxService.store("orders.v1.order-created", event);
        return order;
    }
}
