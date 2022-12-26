package com.amigoscode.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
