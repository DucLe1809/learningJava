package org.learningspringwithduc.loginservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.loginservice.dtos.LoginRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final RestTemplate restTemplate;

    public String login(String username, String password) {
        String url = "http://localhost:8081/users/get-by-username/" + username;

        LoginRequest user = restTemplate.getForObject(url, LoginRequest.class);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }

        return "mock-jwt" + username;
    }

}
