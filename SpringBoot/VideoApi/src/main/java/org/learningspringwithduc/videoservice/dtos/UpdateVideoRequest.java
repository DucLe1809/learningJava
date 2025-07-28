package org.learningspringwithduc.videoservice.dtos;


import lombok.Data;

@Data
public class UpdateVideoRequest {
    private String title;
    private String description;
    private int duration;
    private String tag;
}
