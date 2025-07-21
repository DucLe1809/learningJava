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

    public String login(String username, String password) {
        String url = "http://localhost:8081/users/get-by-username/" + username;

        LoginRequest user = restTemplate.getForObject(url, LoginRequest.class);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtils.createToken(username);
    }

    public String signUp(SignUpRequest request) {
        String url = "http://localhost:8081/users/sign-up-user";

        try {
            SignUpResponse response = restTemplate.postForObject(url, request, SignUpResponse.class);
            return "A new user is successfully created";
        } catch (HttpClientErrorException.Conflict e) {
            return "Email already used";
        } catch (HttpClientErrorException ex) {
            return "Error" + ex.getMessage();
        }
    }

}
