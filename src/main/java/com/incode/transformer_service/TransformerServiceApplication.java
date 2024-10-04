package com.incode.transformer_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main application class for the Transformer Service.
 */
@SpringBootApplication
public class TransformerServiceApplication {

    /**
     * Main method to run the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(
                TransformerServiceApplication.class, args);
    }

    /**
     * Run a command line runner on startup.
     *
     * @return The command line runner.
     */
    @Bean
    public CommandLineRunner runOnStartup() {
        return args -> {
            System.out.println(
                    "Transformer Service Application has started!"
            );
        };
    }
}
