package com.actuator.actuator_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ActuatorTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorTestApplication.class, args);
	}

	@GetMapping
	public String greatMe() {
		return "hello motherfucker";
	}

}
