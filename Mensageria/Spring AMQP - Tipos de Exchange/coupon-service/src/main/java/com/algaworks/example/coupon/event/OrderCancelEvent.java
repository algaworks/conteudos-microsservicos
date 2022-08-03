package com.algaworks.example.coupon.event;

import com.algaworks.example.coupon.model.OrderModel;

import java.time.OffsetDateTime;

public class OrderCancelEvent {
    private OffsetDateTime date = OffsetDateTime.now();
    private OrderModel order;

    public OrderCancelEvent() {
    }

    public OrderCancelEvent(OrderModel order) {
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
