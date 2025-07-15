package org.learningspringwithduc.videoapi.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.videoapi.dtos.RegisterVideoRequest;
import org.learningspringwithduc.videoapi.dtos.UpdateVideoRequest;
import org.learningspringwithduc.videoapi.dtos.VideoDto;
import org.learningspringwithduc.videoapi.mappers.VideoMapper;
import org.learningspringwithduc.videoapi.repositories.VideoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;
    private final VideoMapper videoMapper;

    // READ ALL VIDEO
    @GetMapping
    public List<VideoDto> getAllVideos(){
        return videoService.getAllVideos()
                .stream()
                .map(videoMapper::toDto)
                .toList();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable Long id){
        return videoService.getVideoById(id)
                .map(videoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody RegisterVideoRequest request){
        var video = videoMapper.toEntity(request);
        videoService.createVideo(video);

        var videoDto = videoMapper.toDto(video);
        return  ResponseEntity.status(HttpStatus.CREATED).body(videoDto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> updateVideo(
            @PathVariable(name = "id") Long id, @RequestBody UpdateVideoRequest request){
        var video = videoService.getVideoById(id).orElse(null);

        if (video == null) {
            return ResponseEntity.notFound().build();
        }

        videoMapper.update(request, video);
        videoService.createVideo(video);

        return ResponseEntity.ok(videoMapper.toDto(video));
    }

    // DELETE
    
}
