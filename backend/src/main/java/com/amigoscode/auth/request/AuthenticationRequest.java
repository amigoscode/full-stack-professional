package com.amigoscode.auth.request;

public record AuthenticationRequest(
        String username,
        String password
) {
}
