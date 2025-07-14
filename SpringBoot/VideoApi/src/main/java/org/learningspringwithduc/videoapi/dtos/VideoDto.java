package org.learningspringwithduc.videoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class VideoDto {
    private Long id;
    private String titles;
    private String description;
}
