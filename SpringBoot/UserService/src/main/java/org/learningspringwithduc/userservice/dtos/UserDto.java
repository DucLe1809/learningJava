package org.learningspringwithduc.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String number;
}
