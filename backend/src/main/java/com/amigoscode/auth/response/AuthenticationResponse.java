package com.amigoscode.auth.response;

import com.amigoscode.customer.dto.CustomerDTO;

public record AuthenticationResponse (
        String token,
        CustomerDTO customerDTO){
}
