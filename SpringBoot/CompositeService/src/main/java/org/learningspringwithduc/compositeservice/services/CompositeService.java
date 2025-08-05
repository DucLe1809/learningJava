package org.learningspringwithduc.compositeservice.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.compositeservice.dtos.VideoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompositeService {
    private final RestTemplate restTemplate;

    @Value("${videos.uri}")
    private String videoUri;

    @Value("${recommend.uri}")
    private String recommendUri;

    @Value("${users.uri}")
    private String usersUri;

    // Get list of id videos according to userId (if client in whitelist)
    public List<Long> getListIds(Long userId) {
        if (inWhiteList(userId)) {
            return getRecommendedVideoIds(userId);
        }
        return getDefaultVideoIds();
    }

    // Call to VideoService to get all video in the id list
    public List<VideoDto> getAllVideoByIds(List<Long> videoIds) {
        String videoUrl = usersUri + "get-list-videos";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // New body to send the request
        HttpEntity<List<Long>> httpEntity = new HttpEntity<>(videoIds, headers);

        return restTemplate.exchange(videoUrl,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<List<VideoDto>>() {}
        ).getBody();
    }

    // Get list of video ids in VideoService
    public List<Long> getDefaultVideoIds() {
        String defaultUrl = videoUri;
        return restTemplate.exchange(defaultUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Long>>() {}
        ).getBody();
    }


    // Get list of video ids in RecommendService
    public List<Long> getRecommendedVideoIds(Long userId) {
        String recommendedUrl = recommendUri + userId + "?limit=1&offset=0";
        return restTemplate.exchange(recommendedUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Long>>() {}
        ).getBody();
    }

    // Check if client in white list
    public Boolean inWhiteList(Long userId) {
        String url = usersUri + userId + "/is-white-list";
        return restTemplate.getForObject(url, Boolean.class);
    }
}
