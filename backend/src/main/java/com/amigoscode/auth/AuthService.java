package com.amigoscode.auth;

import com.amigoscode.customer.Customer;
import com.amigoscode.jwt.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager,
                       JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        Customer customer = (Customer) authenticate.getPrincipal();
        String username = customer.getUsername();
        List<String> authorities = customer.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return jwtUtil.issueToken(username, authorities);
    }

}
