package org.learningspringwithduc.loginservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.loginservice.dtos.LoginRequest;
import org.learningspringwithduc.loginservice.dtos.LoginResponse;
import org.learningspringwithduc.loginservice.dtos.SignUpRequest;
import org.learningspringwithduc.loginservice.dtos.SignUpResponse;
import org.learningspringwithduc.loginservice.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginServices {

    private final RestTemplate restTemplate;
    private final JwtUtils jwtUtils;

    public String logIn(LoginRequest request) {
        String url = "http://localhost:8081/users/verify-user";

        try {
            LoginResponse validUser = restTemplate.postForObject(url, request, LoginResponse.class);
            String token = jwtUtils.createToken(validUser);
            return token;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public SignUpResponse signUp(SignUpRequest request) {
        String url = "http://localhost:8081/users/sign-up-user";

        try {
            return restTemplate.postForObject(url, request, SignUpResponse.class);
        } catch (HttpClientErrorException.Conflict e) {
            throw new RuntimeException("Email already exists");
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
