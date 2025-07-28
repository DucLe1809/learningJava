package org.learningspringwithduc.compositeservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.compositeservice.dtos.VideoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompositeService {
    private final RestTemplate restTemplate;

    public List<VideoDto> getVideos(Long userId) {
        if (inWhiteList(userId)) {
            return getRecommededVideoIds(userId);
        }
        return getDefaultVideoIds(userId);
    }

    public Boolean inWhiteList(Long userId) {
        String url = "https://localhost:8081/users/" + userId + "/whitelist";
        return restTemplate.getForObject(url, Boolean.class);
    }

    public List<VideoDto> getRecommededVideoIds(Long userId) {
        String recommendedUrl = "https://localhost:8085/recommended/" + userId;
        return restTemplate.getForObject(recommendedUrl, List.class);
    }

    public List<VideoDto> getDefaultVideoIds(Long userId) {
        String defaultUrl = "https://localhost:8084/videos/" + userId;
        return restTemplate.getForObject(defaultUrl, List.class);
    }
}
