package org.learningspringwithduc.videoapi.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.videoapi.dtos.VideoDto;
import org.learningspringwithduc.videoapi.mappers.VideoMapper;
import org.learningspringwithduc.videoapi.repositories.VideoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;
    private final VideoMapper videoMapper;

    // Read all video
    @GetMapping
    public List<VideoDto> getAllVideos(){
        return videoService.getAllVideos()
                .stream()
                .map(videoMapper::toDto)
                .toList();
    }

    // Read by id
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable Long id){
        return videoService.getVideoById(id)
                .map(videoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create
}
