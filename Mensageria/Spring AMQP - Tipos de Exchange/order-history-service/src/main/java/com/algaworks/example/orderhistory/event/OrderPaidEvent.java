package com.algaworks.example.orderhistory.event;

import com.algaworks.example.orderhistory.model.OrderModel;

import java.time.OffsetDateTime;

public class OrderPaidEvent {
    private OffsetDateTime date = OffsetDateTime.now();
    private OrderModel order;

    public OrderPaidEvent() {
    }

    public OrderPaidEvent(OrderModel order) {
        this.order = order;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
