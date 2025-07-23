package org.learningspringwithduc.loginservice.controllers;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.loginservice.dtos.LoginRequest;
import org.learningspringwithduc.loginservice.dtos.LoginResponse;
import org.learningspringwithduc.loginservice.dtos.SignUpRequest;
import org.learningspringwithduc.loginservice.dtos.SignUpResponse;
import org.learningspringwithduc.loginservice.services.LoginService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = loginService.login(request);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    // Sign Up
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        String response = loginService.signUp(request);
        return ResponseEntity.ok(new SignUpResponse(response));
    }

}
