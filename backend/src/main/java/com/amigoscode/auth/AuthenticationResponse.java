package com.amigoscode.auth;

import com.amigoscode.customer.CustomerDTO;

public record AuthenticationResponse (
        String token,
        CustomerDTO customerDTO){
}
