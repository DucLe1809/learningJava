package org.learningspringwithduc.videoapi.repositories;

import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepositories extends JpaRepository<VideoEntities, Long> {
}
