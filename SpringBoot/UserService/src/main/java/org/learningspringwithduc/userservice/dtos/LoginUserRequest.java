package org.learningspringwithduc.userservice.dtos;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String username;
    private String password;
}
