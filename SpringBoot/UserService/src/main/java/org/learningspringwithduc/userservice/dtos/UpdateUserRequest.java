package org.learningspringwithduc.userservice.dtos;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String email;
    private String number;
}
