package org.learningspringwithduc.recommendedvideo.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.recommendedvideo.entities.RecommendedEntities;
import org.learningspringwithduc.recommendedvideo.services.RecommendedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Long>> getRecommendedVideoIds(
            @RequestParam int limit,
            @RequestParam int offset,
            @PathVariable Long id) {
       List<RecommendedEntities> allVideos = recommendedService.getAllVideoIds();

       Random random = new Random(id);

       Collections.shuffle(allVideos, random);

       // Extract id of videos
       List<Long> recommendedIds = allVideos.stream()
               .skip(offset)
               .limit(limit)
               .map(RecommendedEntities::getId)
               .collect(Collectors.toList());

       return ResponseEntity.ok(recommendedIds);
    }
}
