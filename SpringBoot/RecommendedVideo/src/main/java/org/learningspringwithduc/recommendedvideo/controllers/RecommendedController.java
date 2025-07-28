package org.learningspringwithduc.recommendedvideo.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.recommendedvideo.dtos.VideoIdDto;
import org.learningspringwithduc.recommendedvideo.entities.RecommendedEntities;
import org.learningspringwithduc.recommendedvideo.mapper.RecommendedMapper;
import org.learningspringwithduc.recommendedvideo.services.RecommendedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recommended")
public class RecommendedController {
    private final RecommendedService recommendedService;
    private final RecommendedMapper recommendedMapper;

    // Mock list of recommended ids
    @GetMapping("/{id}")
    public ResponseEntity<List<VideoIdDto>> getRecommendedVideoIds(@PathVariable Long id) {
       List<RecommendedEntities> recommendedVideos = recommendedService.getAllVideoIds();

       List<VideoIdDto> recommendedIds = recommendedVideos.stream()
               .map(recommendedMapper::entityToDto)
               .toList();

       return ResponseEntity.ok(recommendedIds);
    }
}
