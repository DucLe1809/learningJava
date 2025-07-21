package org.learningspringwithduc.userservice.mappers;

import org.learningspringwithduc.userservice.dtos.LoginUserRequest;
import org.learningspringwithduc.userservice.dtos.UserDto;
import org.learningspringwithduc.userservice.dtos.RegisterUserRequest;
import org.learningspringwithduc.userservice.dtos.UpdateUserRequest;
import org.learningspringwithduc.userservice.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto entityToDto(User user);
    LoginUserRequest entityToLoginUserRequest(User user);
    User registerToUser(RegisterUserRequest registerUserRequest);
    void update(UpdateUserRequest updateUserRequest, @MappingTarget User user);

}
