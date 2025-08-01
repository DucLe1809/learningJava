package org.learningspringwithduc.videoservice.controllers;

import lombok.AllArgsConstructor;
import org.learningspringwithduc.videoservice.dtos.RegisterVideoRequest;
import org.learningspringwithduc.videoservice.dtos.UpdateVideoRequest;
import org.learningspringwithduc.videoservice.dtos.VideoDto;
import org.learningspringwithduc.videoservice.entities.VideoEntities;
import org.learningspringwithduc.videoservice.mappers.VideoMapper;
import org.learningspringwithduc.videoservice.services.VideoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;
    private final VideoMapper videoMapper;

    // GET ALL VIDEO
    @GetMapping
    public ResponseEntity<List<Long>> getAllVideoIds() {
        List<Long> allVideoIds = videoService.getAllVideos()
                .stream()
                .map(VideoEntities::getId)
                .collect(Collectors.toList());
        return ResponseEntity.ok(allVideoIds);
    }

    // GET VIDEO BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable Long id){
        Optional<VideoEntities> video = videoService.getVideoById(id);
        if (video.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(video.map(videoMapper::toDto).get());
    }


    // GET MULTIPLE VIDEOS BY LIST OF ID
    @PostMapping("/get-list-videos")
    public ResponseEntity<List<VideoDto>> getAllVideosList(@RequestBody List<Long> ids) {
        List<VideoDto> videoDtoList = videoService.getAllVideosById(ids).stream()
                                    .map(videoMapper::toDto)
                                    .collect(Collectors.toList());
        return ResponseEntity.ok(videoDtoList);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable(name = "id") Long id){
        var video = videoService.getVideoById(id).orElse(null);
        if (video == null) {
            return ResponseEntity.notFound().build();
        }

        videoService.deleteVideoById(id);
        return ResponseEntity.noContent().build();
    }
}
