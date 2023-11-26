package com.trabalhoFinal;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Final Project WEB Programming 2", description = "Final Project WEB Programming 2 - FURB from discipline 'Programação Web II'"))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}