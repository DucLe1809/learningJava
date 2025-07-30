package org.learningspringwithduc.recommendedvideo.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.recommendedvideo.entities.RecommendedEntities;
import org.learningspringwithduc.recommendedvideo.services.RecommendedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/recommended")
public class RecommendedController {
    private final RecommendedService recommendedService;

    // Mock list of recommended ids
    @GetMapping("/{id}")
    public ResponseEntity<List<Long>> getRecommendedVideoIds(@PathVariable Long id) {
       List<RecommendedEntities> recommendedVideos = recommendedService.getAllVideoIds();

       Random random = new Random(id);

       Collections.shuffle(recommendedVideos, random);
       
       // Extract id of videos
       List<Long> recommendedIds = recommendedVideos.stream()
               .limit(5)
               .map(RecommendedEntities::getId)
               .collect(Collectors.toList());

       return ResponseEntity.ok(recommendedIds);
    }
}
