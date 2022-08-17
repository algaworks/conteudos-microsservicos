package com.algaworks.example.order.model;

import com.algaworks.example.order.domain.Order;

import java.math.BigDecimal;

public class OrderModel {
	private Long id;
	private BigDecimal value = BigDecimal.ZERO;
	private Boolean paid = Boolean.FALSE;

	public OrderModel() {
	}

	public OrderModel(Long id, BigDecimal value, Boolean paid) {
		this.id = id;
		this.value = value;
		this.paid = paid;
	}

	public static OrderModel of(Order order) {
		return new OrderModel(order.getId(),order.getValue(), order.isPaid());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public void markAsPaid() {
		this.paid = true;
	}
}
