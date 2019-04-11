package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaeApplication.class, args);
	}
	
	@GetMapping("/get")
	public String getval()
	{
		return "mmmmmmmm";
	}

}
