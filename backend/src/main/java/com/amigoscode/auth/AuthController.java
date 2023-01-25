package com.amigoscode.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .build();
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }

}
