package com.mevy.metales_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MetalesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetalesBackendApplication.class, args);
	}
	
}
