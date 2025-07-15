package org.learningspringwithduc.videoapi.mappers;

import org.learningspringwithduc.videoapi.dtos.RegisterVideoRequest;
import org.learningspringwithduc.videoapi.dtos.VideoDto;
import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoDto toDto(VideoEntities video);
    VideoEntities toEntity(RegisterVideoRequest request);
}
