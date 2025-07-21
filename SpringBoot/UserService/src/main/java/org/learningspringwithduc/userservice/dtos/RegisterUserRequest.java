package org.learningspringwithduc.userservice.dtos;


import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
    private String number;
}
