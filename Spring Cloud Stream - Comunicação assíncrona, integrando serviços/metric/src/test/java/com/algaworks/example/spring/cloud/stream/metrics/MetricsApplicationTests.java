package com.algaworks.example.spring.cloud.stream.metrics;

import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckTaskResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;

class MetricsApplicationTests {

	@Test
	void contextLoads() {

		Duration.parse("").toMillis();

		var today1h = OffsetDateTime.of(
				2022, 1, 1,
				1, 0,0, 0, ZoneOffset.UTC
		);
		var today2 = OffsetDateTime.of(
				2022, 1, 1,
				1, 2,0, 0, ZoneOffset.UTC
		);

		long seconds = Duration.between(today1h, today2).getSeconds();

		System.out.println(seconds);
	}

}
