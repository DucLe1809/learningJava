package org.learningspringwithduc.recommendedvideo.repositories;

import org.learningspringwithduc.recommendedvideo.entities.RecommendedEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendedRepositories extends JpaRepository<RecommendedEntities, Long> {
    List<RecommendedEntities> findByIdIn(List<Long> ids);
}
