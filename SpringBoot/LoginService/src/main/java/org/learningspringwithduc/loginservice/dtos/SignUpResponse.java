package org.learningspringwithduc.loginservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpResponse {
    private Long id;
    private String username;
    private String email;
    private String number;
}
