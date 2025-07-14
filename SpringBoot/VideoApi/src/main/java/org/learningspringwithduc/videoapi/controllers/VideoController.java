package org.learningspringwithduc.videoapi.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.videoapi.dtos.VideoDto;
import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.learningspringwithduc.videoapi.mappers.VideoMapper;
import org.learningspringwithduc.videoapi.repositories.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final VideoMapper videoMapper;

    @GetMapping("/videos")
    public List<VideoDto> getAllVideos(){
        return videoService.getAllVideos()
                .stream()
                .map(videoMapper::toDto)
                .toList();
    }
}
