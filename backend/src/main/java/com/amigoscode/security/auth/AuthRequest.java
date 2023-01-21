package com.amigoscode.security.auth;

public record AuthRequest(
        String username,
        String password
) {
}
