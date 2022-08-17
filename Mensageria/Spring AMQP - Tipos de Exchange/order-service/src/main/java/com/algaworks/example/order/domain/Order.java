package com.algaworks.example.order.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal value = BigDecimal.ZERO;
	private Boolean paid = Boolean.FALSE;

	public Order() {
	}

	public Order(BigDecimal value, Boolean paid) {
		this.value = value;
		this.paid = paid;
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

	public void cancel() {
		this.paid = false;
	}
}
