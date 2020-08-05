package com.webapplication.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication initializes Spring (Component Scan) and Spring Boot (Auto Configuration).
@SpringBootApplication
public class WebApplicationWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplicationWithSpringBootApplication.class, args);
		// SpringApplication.run launches a Spring Boot Application
	}

}
