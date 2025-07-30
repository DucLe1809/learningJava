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

    @GetMapping("/{id}")
    public ResponseEntity<List<VideoDto>> getListVideoIds(@PathVariable Long id) {
        List<VideoDto> listIds = compositeService.getVideos(id);
        return ResponseEntity.ok(listIds);
    }
}
