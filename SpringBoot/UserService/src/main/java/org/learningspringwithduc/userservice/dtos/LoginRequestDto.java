package org.learningspringwithduc.userservice.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
