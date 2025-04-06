package com.project.demo;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"CreditDemo", "com.project.demo"})
@EntityScan(basePackages = "model")
public class Tab25MwbszApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tab25MwbszApplication.class, args);
	}

}
