package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer{
	@GetMapping("/ping")
	public String ping(){
		return "pong";
	}
	@GetMapping("/ping2")
	public String ping(){
		return "pong2";
	}
	public static void main(String[] args) {
		System.out.println("######### Running the Demo Application ##############");
		SpringApplication.run(DemoApplication.class, args);
	}

}
