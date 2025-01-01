package com.jpacourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 * This class contains the main method that launches the Spring Boot application
 * and initializes the context for the application.
 * <p>
 * The {@link SpringBootApplication} annotation enables auto-configuration,
 * component scanning, and configuration for a Spring Boot application.
 */
@SpringBootApplication
public class WsbJpaApplication {

    /**
     * The main method that starts the Spring Boot application.
     * This method triggers the bootstrapping process of the application,
     * initializing the Spring context and running the application.
     *
     * @param args command-line arguments passed to the application at startup
     */
    public static void main(String[] args) {
        SpringApplication.run(WsbJpaApplication.class, args);
    }
}
