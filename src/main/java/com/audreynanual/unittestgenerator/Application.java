// This is the main entry point for the Spring Boot application.
// It initializes the Spring context and wires together the different components of the application.

package com.audreynanual.unittestgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		

		//‘SpringApplication.run’ is the entry point to the Spring application
		SpringApplication.run(Application.class, args);
	}

}