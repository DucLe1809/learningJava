package org.learningspringwithduc.recommendedvideo.mapper;

import org.learningspringwithduc.recommendedvideo.dtos.VideoIdDto;
import org.learningspringwithduc.recommendedvideo.entities.RecommendedEntities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecommendedMapper {
    VideoIdDto entityToDto(RecommendedEntities recommended);
}
