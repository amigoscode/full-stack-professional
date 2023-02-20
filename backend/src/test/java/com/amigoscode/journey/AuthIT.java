package com.amigoscode.journey;

import com.amigoscode.auth.LoginRequest;
import com.amigoscode.customer.CustomerRegistrationRequest;
import com.amigoscode.customer.Gender;
import com.amigoscode.jwt.JWTUtil;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthIT {

    private static final String AUTH_PATH = "/api/v1/auth";
    private static final String CUSTOMER_PATH = "/api/v1/customers";
    private static final Random RANDOM = new Random();

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void canPerformLogin() {
        // Given a request
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String name = fakerName.fullName();
        int age = RANDOM.nextInt(1, 100);

        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;

        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@amigoscode.com";

        String password = "password";

        LoginRequest loginRequest = new LoginRequest(
                email,
                password
        );

        // login with user that does not exist
        webTestClient.post()
                .uri(String.format("%s/login", AUTH_PATH))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .exchange()
                .expectStatus()
                .isUnauthorized();

        // create the user

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                name, email, password, age, gender
        );

        // send a post request to create customer
        webTestClient.post()
                .uri(CUSTOMER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // Attempt to log in again with user that exist
        String jwtToken = webTestClient.post()
                .uri(String.format("%s/login", AUTH_PATH))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        // Then verify that token is valid
        String subject = jwtUtil.getSubject(jwtToken);
        assertThat(subject).isEqualTo(email);

        boolean tokenValid = jwtUtil.isTokenValid(jwtToken, email);
        assertThat(tokenValid).isTrue();
    }
}
