package org.learningspringwithduc.videoservice.mappers;

import org.learningspringwithduc.videoservice.dtos.RegisterVideoRequest;
import org.learningspringwithduc.videoservice.dtos.UpdateVideoRequest;
import org.learningspringwithduc.videoservice.dtos.VideoDto;
import org.learningspringwithduc.videoservice.entities.VideoEntities;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoDto toDto(VideoEntities video);
    VideoEntities toEntity(RegisterVideoRequest request);
    void update(UpdateVideoRequest request, @MappingTarget VideoEntities videoTarget);
}
