package com.amigoscode.customer.request;

public record CustomerRegistrationRequest(
        String name,
        String email,
        String password,
        Integer age,
        Gender gender
) {
}
