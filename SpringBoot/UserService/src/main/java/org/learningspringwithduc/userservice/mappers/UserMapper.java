package org.learningspringwithduc.userservice.mappers;

import org.learningspringwithduc.userservice.dtos.*;
import org.learningspringwithduc.userservice.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto entityToDto(User user);

    LoginRequestDto entityToLoginUserRequest(User user);

    User registerToUser(RegisterUserRequest registerUserRequest);

    LoginResponseDto entityToLoginResponse(User user);

    void update(UpdateUserRequest updateUserRequest, @MappingTarget User user);

}
