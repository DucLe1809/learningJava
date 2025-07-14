package org.learningspringwithduc.videoapi.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.learningspringwithduc.videoapi.repositories.VideoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VideoController {
    private final VideoRepositories  videoRepositories;

    @GetMapping("/videos")
    public List<VideoEntities> getAllVideos(){
        return videoRepositories.findAll();
    }
}
