package com.example.springamqp.aula1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

	@Autowired
	private OrderRepository orders;
	
	@PostMapping
	public Order create(@RequestBody Order order) {
		orders.save(order);
		return order;
	}

	@GetMapping
	public Collection<Order> list() {
		return orders.findAll();
	}

	@GetMapping("{id}")
	public Order findById(@PathVariable Long id) {
		return orders.findById(id).orElseThrow();
	}

	@PutMapping("{id}/pay")
	public Order pay(@PathVariable Long id) {
		Order order = orders.findById(id).orElseThrow();
		order.markAsPaid();
		return orders.save(order);
	}
	
}
