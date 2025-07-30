package org.learningspringwithduc.compositeservice.controllers;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.compositeservice.dtos.VideoDto;
import org.learningspringwithduc.compositeservice.services.CompositeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/composite")
@RequiredArgsConstructor
public class CompositeController {
    private final CompositeService compositeService;

    @GetMapping("/get-list-video")
    public ResponseEntity<List<VideoDto>> getListVideoIds(
            @RequestHeader(value = "X-User-Id", required = false) String userIdStr) {
        List<VideoDto> listIds = compositeService.getVideos(Long.parseLong(userIdStr));
        return ResponseEntity.ok(listIds);
    }
}
