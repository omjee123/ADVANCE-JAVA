package com.example.SpringSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class TokenDTO {
    private String activeToken;
    private String refreshToken;
}
