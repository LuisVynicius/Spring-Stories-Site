package com.mevy.stories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class StoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoriesApplication.class, args);
	}

}
