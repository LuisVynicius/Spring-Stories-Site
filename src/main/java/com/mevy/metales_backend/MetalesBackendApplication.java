package com.mevy.metales_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MetalesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetalesBackendApplication.class, args);
	}
	
}
