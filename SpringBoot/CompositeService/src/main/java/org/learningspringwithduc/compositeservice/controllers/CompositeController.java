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

    @GetMapping("/get-list-videos")
    public ResponseEntity<List<VideoDto>> getListVideoIds(
            @RequestHeader("X-User-Id") String userIdStr) {
        System.out.println("Received Id: " +  userIdStr);

        List<Long> listIds;
        if (userIdStr == null || userIdStr.isEmpty()) {
            listIds = compositeService.getDefaultVideoIds();
        }
        else {
            listIds = compositeService.getListIds(Long.parseLong(userIdStr));
        }
        return ResponseEntity.ok(compositeService.getAllVideoByIds(listIds));
    }
}
