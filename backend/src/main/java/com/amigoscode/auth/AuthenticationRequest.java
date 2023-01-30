package com.amigoscode.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
