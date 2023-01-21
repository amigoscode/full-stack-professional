package com.amigoscode.customer;

public record CustomerRegistrationRequest(
        String username,
        String password,
        String name,
        String email,
        Integer age,
        Gender gender
) {
}
