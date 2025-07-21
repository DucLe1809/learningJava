package org.learningspringwithduc.loginservice.dtos;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String number;
}
