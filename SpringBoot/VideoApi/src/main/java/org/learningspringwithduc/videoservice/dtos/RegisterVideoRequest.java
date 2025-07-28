package org.learningspringwithduc.videoservice.dtos;

import lombok.Data;

@Data
public class RegisterVideoRequest {
    private String name;
    private String title;
    private String description;
}
