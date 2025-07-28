package org.learningspringwithduc.videoservice.repositories;

import org.learningspringwithduc.videoservice.entities.VideoEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepositories extends JpaRepository<VideoEntities, Long> {
}
