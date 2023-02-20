package com.amigoscode.auth;

public record LoginRequest(
        String username,
        String password
) {
}
