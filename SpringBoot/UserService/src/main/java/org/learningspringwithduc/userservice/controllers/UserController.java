package org.learningspringwithduc.userservice.controllers;

import lombok.AllArgsConstructor;

import org.learningspringwithduc.userservice.dtos.*;
import org.learningspringwithduc.userservice.mappers.UserMapper;
import org.learningspringwithduc.userservice.repositories.UserRepositories;
import org.learningspringwithduc.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepositories userRepositories;

    // READ ALL USER
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::entityToDto)
                .toList();
    }

    // READ USER BY ID
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(userMapper::entityToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ USER BY USERNAME
    @GetMapping("/get-by-username/{username}")
    public ResponseEntity<LoginRequestDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(userMapper::entityToLoginUserRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/is-white-list")
    public ResponseEntity<Boolean> userIsWhiteList(@PathVariable Long id) {
        Boolean isWhiteList = userService.isWhiteList(id);
        if (isWhiteList) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    // VERIFY USER FOR LOG IN
    @PostMapping("/verify-user")
    public ResponseEntity<?> verifyUser(@RequestBody LoginRequestDto request) {
        try {
            LoginResponseDto validUser = userService.logIn(request);
            return ResponseEntity.ok(validUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // CREATE USER FOR SIGN UP
    @PostMapping("/sign-up-user")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest request){
        try {
            UserDto newUser = userService.signUpUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Email already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // UPDATE USER
    @PutMapping("/update-user-data/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id, @RequestBody UpdateUserRequest request){
        var user = userService.getUserById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userMapper.update(request, user);
        userRepositories.save(user);

        return ResponseEntity.ok(userMapper.entityToDto(user));
    }

}
