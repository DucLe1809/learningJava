package org.learningspringwithduc.videoapi.mappers;

import org.hibernate.sql.Update;
import org.learningspringwithduc.videoapi.dtos.RegisterVideoRequest;
import org.learningspringwithduc.videoapi.dtos.UpdateVideoRequest;
import org.learningspringwithduc.videoapi.dtos.VideoDto;
import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoDto toDto(VideoEntities video);
    VideoEntities toEntity(RegisterVideoRequest request);
    void update(UpdateVideoRequest request, @MappingTarget VideoEntities videoTarget);
}
