package com.amigoscode.customer.dto;

import com.amigoscode.customer.request.Gender;

import java.util.List;

public record CustomerDTO(
        Integer id,
        String name,
        String email,
        Gender gender,
        Integer age,
        List<String> roles,
        String username,
        String profileImageId
) {

}
