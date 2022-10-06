package com.example.algaworks.spring.cloud.stream.webpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class AlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

}
