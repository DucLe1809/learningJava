package org.learningspringwithduc.recommendedvideo.services;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.recommendedvideo.entities.RecommendedEntities;
import org.learningspringwithduc.recommendedvideo.repositories.RecommendedRepositories;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendedService {
    private final RecommendedRepositories recommendedRepositories;

    // Stimulate the returning of recommended video
    public List<RecommendedEntities> getAllVideoIds() {
        List<Long> listOfIds = Arrays.asList(1L, 3L, 4L);

        return recommendedRepositories.findByIdIn(listOfIds);
    }
}
