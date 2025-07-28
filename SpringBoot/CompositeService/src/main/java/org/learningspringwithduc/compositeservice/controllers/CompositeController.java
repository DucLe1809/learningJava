package org.learningspringwithduc.compositeservice.controllers;

import lombok.RequiredArgsConstructor;
import org.learningspringwithduc.compositeservice.dtos.VideoDto;
import org.learningspringwithduc.compositeservice.services.CompositeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/composite")
@RequiredArgsConstructor
public class CompositeController {
    private final CompositeService compositeService;

    @GetMapping("/{id}")
    public ResponseEntity<List<VideoDto>> getListVideoIds(@RequestParam Long userId) {
        List<VideoDto> listIds = compositeService.getVideos(userId);
        return ResponseEntity.ok(listIds);
    }
}
