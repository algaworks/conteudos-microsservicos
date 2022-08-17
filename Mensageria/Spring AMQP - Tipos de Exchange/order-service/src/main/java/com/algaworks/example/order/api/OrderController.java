package com.algaworks.example.order.api;

import com.algaworks.example.order.domain.OrderRepository;
import com.algaworks.example.order.domain.Order;
import com.algaworks.example.order.event.OrderCreatedEvent;
import com.algaworks.example.order.model.OrderInputModel;
import com.algaworks.example.order.model.OrderModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

	@Autowired
	private OrderRepository orders;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping
	public OrderModel create(@RequestBody OrderInputModel order) {
		return OrderModel.of(orders.save(order.toOrder()));
	}

	@GetMapping
	public List<OrderModel> list() {
		return orders.findAll().stream().map(OrderModel::of).toList();
	}

	@GetMapping("{id}")
	public OrderModel findById(@PathVariable Long id) {
		return OrderModel.of(orders.findById(id).orElseThrow());
	}

	@PostMapping("{id}/pay")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void pay(@PathVariable Long id) {
		Order order = orders.findById(id).orElseThrow();
		order.markAsPaid();
		orders.save(order);
	}

	@PostMapping("{id}/cancel")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancel(@PathVariable Long id) {
		Order order = orders.findById(id).orElseThrow();
		order.cancel();
		orders.save(order);
	}
	
}
