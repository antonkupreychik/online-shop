package com.kupreychik.ratesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories(basePackages = "com.kupreychik.ratesservice.repository")
@SpringBootApplication
public class RatesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesServiceApplication.class, args);
	}

}
