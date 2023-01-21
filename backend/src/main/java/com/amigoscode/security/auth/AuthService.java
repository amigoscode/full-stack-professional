package com.amigoscode.security.auth;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import com.amigoscode.exception.ResourceNotFoundException;
import com.amigoscode.security.jwt.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(CustomerRepository customerRepository,
                       JWTUtil jwtUtil,
                       AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }


    public AuthResponse authenticate(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        Customer customer = (Customer) authenticate.getPrincipal();

        var jwtToken = jwtUtil.generateToken(customer.getUsername());
        return new AuthResponse(jwtToken);
    }
}