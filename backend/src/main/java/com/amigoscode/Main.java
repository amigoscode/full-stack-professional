package com.amigoscode;

import com.amigoscode.security.auth.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(AuthService authService) {
        return args -> {
//            AuthResponse authResponse = authService.register(
//                    new RegistrationRequest(
//                            "loba", "bom"
//                    )
//            );
//            System.out.println(authResponse);
        };
    }

}
