package com.algaworks.example.ecommerce;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
class OrderApplicationTests {

	@Autowired
	OrderRepository orders;

	@Test
	@SqlGroup({
			@Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD),
			@Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
	})
	void testCount() {
		Integer paidOrderCount = orders.countPaidOrders();
		Assertions.assertThat(paidOrderCount).isEqualTo(4);
	}

	@Test
	@SqlGroup({
			@Sql(value = "classpath:reset.sql", executionPhase = BEFORE_TEST_METHOD),
			@Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
	})
	void testSelect() {
		List<Order> alexOrders = orders.findByCustomer("Alex");
		Assertions.assertThat(alexOrders.size()).isEqualTo(4);
	}


}
