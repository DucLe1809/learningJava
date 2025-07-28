package org.learningspringwithduc.userservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.userservice.dtos.LoginRequestDto;
import org.learningspringwithduc.userservice.dtos.LoginResponseDto;
import org.learningspringwithduc.userservice.dtos.RegisterUserRequest;
import org.learningspringwithduc.userservice.dtos.UserDto;
import org.learningspringwithduc.userservice.entities.User;
import org.learningspringwithduc.userservice.mappers.UserMapper;

import org.learningspringwithduc.userservice.repositories.UserRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositories userRepositories;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepositories.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
         return userRepositories.findByUsername(username);
    }

    public UserDto signUpUser(RegisterUserRequest request) {
        String newUserEmail = request.getEmail();

        // A user is existed with the email
        if (userRepositories.findByEmail(newUserEmail).isPresent()) {
           throw new RuntimeException("Email already exists");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = userMapper.registerToUser(request);
        newUser.setPassword(encodedPassword);

        userRepositories.save(newUser);

        return userMapper.entityToDto(newUser);
    }

    public LoginResponseDto logIn(LoginRequestDto request) {
        Optional<User> user = userRepositories.findByUsername(request.getUsername());

        if (!user.isPresent()) {
            throw new RuntimeException();
        }
        User userEntity = user.get();
        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException();
        }
        return userMapper.entityToLoginResponse(userEntity);
    }

    public void deleteUser(Long id) {
        userRepositories.deleteById(id);
    }

//    public User updateUser(Long id, User newInfo) {
//        return userRepositories.findById(id).map(
//                existingUser -> {
//                    existingUser.setUsername(newInfo.getEmail());
//                    existingUser.setPassword(newInfo.getEmail());
//                    return userRepositories.save(existingUser);
//                }).orElseThrow(() -> new RuntimeException("User not found!"));
//    }

}
