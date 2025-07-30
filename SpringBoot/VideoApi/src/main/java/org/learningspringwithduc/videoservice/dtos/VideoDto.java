package org.learningspringwithduc.videoservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class VideoDto {
    private String title;
    private String description;
    private int duration;
}
