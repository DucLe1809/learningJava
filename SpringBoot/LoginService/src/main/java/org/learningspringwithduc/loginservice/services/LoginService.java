package org.learningspringwithduc.loginservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.loginservice.dtos.LoginRequest;
import org.learningspringwithduc.loginservice.dtos.SignUpRequest;
import org.learningspringwithduc.loginservice.dtos.SignUpResponse;
import org.learningspringwithduc.loginservice.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final RestTemplate restTemplate;
    private final JwtUtils jwtUtils;

    public String login(LoginRequest request) {
        String url = "http://localhost:8081/users/verify-user";

        try {
            Boolean isValid = restTemplate.postForObject(url, request, Boolean.class);
            if (Boolean.TRUE.equals(isValid)) {
                return jwtUtils.createToken(request.getUsername());
            }
            else {
                return "Invalid username or password";
            }
        } catch (HttpClientErrorException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String signUp(SignUpRequest request) {
        String url = "http://localhost:8081/users/sign-up-user";

        try {
            restTemplate.postForObject(url, request, SignUpResponse.class);
            return "A new user is successfully created";
        } catch (HttpClientErrorException.Conflict e) {
            return "Email already used";
        } catch (HttpClientErrorException ex) {
            return "Error" + ex.getMessage();
        }
    }

}
