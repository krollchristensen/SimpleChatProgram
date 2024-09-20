package com.example.simplechatprogramfinal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleChatProgramFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleChatProgramFinalApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {

        };
    }
}
