package org.learningspringwithduc.loginservice.controllers;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.loginservice.dtos.LoginRequest;
import org.learningspringwithduc.loginservice.dtos.TokenResponse;
import org.learningspringwithduc.loginservice.dtos.SignUpRequest;
import org.learningspringwithduc.loginservice.dtos.SignUpResponse;
import org.learningspringwithduc.loginservice.services.LoginServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginServices loginServices;

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = loginServices.logIn(request);
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.
                    status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("Error: ", e.getMessage()));
        }

    }

    // Sign Up
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        try {
            SignUpResponse createdUser = loginServices.signUp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Email already exists")) {
                return ResponseEntity.
                        status(HttpStatus.CONFLICT)
                        .body(Map.of("Error: ", e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("Error: ", e.getMessage()));
        }
    }

}
