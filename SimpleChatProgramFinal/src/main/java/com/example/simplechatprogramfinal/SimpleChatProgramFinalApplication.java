package com.example.simplechatprogramfinal;

import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Entity.Users;
import com.example.simplechatprogramfinal.Usecase.ChatClient;
import com.example.simplechatprogramfinal.Usecase.ChatServer;
import com.example.simplechatprogramfinal.Usecase.ReadServerConfigFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.standard.processor.StandardHrefTagProcessor;

@SpringBootApplication
public class SimpleChatProgramFinalApplication {

    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(SimpleChatProgramFinalApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {

            ChatServer chatServer = new ChatServer();
            new Thread(chatServer::ServerStart).start();

            String email = "mads"; // replace with the email you want to test
            Users user = userRepository.getUserByEmail(email); // Call your method to get the user

            if (user != null) {
                System.out.println("Email: " + user.getEmail());
                System.out.println("Name: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
            } else {
                System.out.println("User not found with email: " + email);
            }
        };
    }


}
