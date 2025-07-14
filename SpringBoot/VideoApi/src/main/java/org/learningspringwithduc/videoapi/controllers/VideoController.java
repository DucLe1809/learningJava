package org.learningspringwithduc.videoapi.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.learningspringwithduc.videoapi.repositories.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/videos")
    public List<VideoEntities> getAllVideos(){
        return videoService.getAllVideos();
    }
}
