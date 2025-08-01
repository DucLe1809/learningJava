package org.learningspringwithduc.compositeservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.compositeservice.dtos.VideoDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompositeService {
    private final RestTemplate restTemplate;

    // Get list of id videos according to userId (if client in whitelist)
    public List<Long> getListIds(Long userId) {
        if (inWhiteList(userId)) {
            return getRecommendedVideoIds(userId);
        }
        return getDefaultVideoIds();
    }

    // Call to VideoService to get all video in the id list
    public List<VideoDto> getAllVideoByIds(List<Long> videoIds) {
        String videoUrl = "http://localhost:8084/videos/get-list-videos";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Long>> httpEntity = new HttpEntity<>(videoIds, headers);

        return restTemplate.exchange(videoUrl,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<VideoDto>>() {}
        ).getBody();
    }

    // Check if client in white list
    public Boolean inWhiteList(Long userId) {
        String url = "http://localhost:8081/users/" + userId + "/is-white-list";
        return restTemplate.getForObject(url, Boolean.class);
    }

    // Get list of video ids in RecommendService
    public List<Long> getRecommendedVideoIds(Long userId) {
        String recommendedUrl = "http://localhost:8085/recommended/" + userId;
        return restTemplate.exchange(recommendedUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Long>>() {}
        ).getBody();
    }

    // Get list of video ids in VideoService
    public List<Long> getDefaultVideoIds() {
        String defaultUrl = "http://localhost:8084/videos";
        return restTemplate.exchange(defaultUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Long>>() {}
        ).getBody();
    }
}
