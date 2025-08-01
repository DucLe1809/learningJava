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

    @GetMapping("/get-list-id/{userIdStr}")
    public ResponseEntity<List<VideoDto>> getListVideoIds(
            @PathVariable String userIdStr) {
        List<Long> listIds = compositeService.getListIds(Long.parseLong(userIdStr));
        List<VideoDto> listVideos = compositeService.getAllVideoByIds(listIds);

        return ResponseEntity.ok(listVideos);
    }
}
