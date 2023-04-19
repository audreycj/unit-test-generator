// This file contains the main method and will be the entry point for the application.
// It will be responsible for parsing command-line arguments using the Picocli library and invoking the appropriate methods based on user input.
// It may also be responsible for initializing any necessary objects or services that your application requires to run.

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