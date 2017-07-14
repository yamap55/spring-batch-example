package com.yamap55.example.spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBatchExample2Application {

	static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(
				BatchConfiguration.class, args)));
	}
}
