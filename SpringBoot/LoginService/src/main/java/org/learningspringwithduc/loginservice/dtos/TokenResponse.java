package org.learningspringwithduc.loginservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenResponse {
    private String token;
}
