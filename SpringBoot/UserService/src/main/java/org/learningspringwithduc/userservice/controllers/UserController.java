package org.learningspringwithduc.userservice.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.userservice.dtos.LoginUserRequest;
import org.learningspringwithduc.userservice.dtos.UserDto;
import org.learningspringwithduc.userservice.dtos.RegisterUserRequest;
import org.learningspringwithduc.userservice.dtos.UpdateUserRequest;
import org.learningspringwithduc.userservice.entities.User;
import org.learningspringwithduc.userservice.mappers.UserMapper;
import org.learningspringwithduc.userservice.repositories.UserRepositories;
import org.learningspringwithduc.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<LoginUserRequest> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(userMapper::entityToLoginUserRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // VERIFY USER FOR LOG IN
    @PostMapping("/verify-user")
    public ResponseEntity<Boolean> verifyUser(@RequestBody LoginUserRequest request) {
        Optional<User> requestUser = userRepositories.findByUsername(request.getUsername());
        if (requestUser.isPresent() && requestUser.get().getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok(true);
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }

    // CREATE USER
    @PostMapping("/sign-up-user")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest request){
        User newUser = userMapper.registerToUser(request);

        User signedUpUser = userService.saveUser(newUser);
        if (signedUpUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email has already been used!");
        }

        var userDto = userMapper.entityToDto(newUser);
        return  ResponseEntity.status(HttpStatus.CREATED).body(userDto);
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
        userService.saveUser(user);

        return ResponseEntity.ok(userMapper.entityToDto(user));
    }

}
