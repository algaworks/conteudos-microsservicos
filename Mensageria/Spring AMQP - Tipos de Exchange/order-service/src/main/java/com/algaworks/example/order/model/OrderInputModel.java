package com.algaworks.example.order.model;

import com.algaworks.example.order.domain.Order;

import java.math.BigDecimal;

public class OrderInputModel {
	private BigDecimal value = BigDecimal.ZERO;
	private Boolean paid = Boolean.FALSE;

	public OrderInputModel() {
	}

	public Order toOrder() {
		return new Order(value,paid);
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
