package org.learningspringwithduc.videoapi.services;

import org.learningspringwithduc.videoapi.entities.VideoEntities;
import org.learningspringwithduc.videoapi.repositories.VideoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private final VideoRepositories videoRepositories;

    public VideoService(VideoRepositories videoRepositories) {
        this.videoRepositories = videoRepositories;
    }

    public List<VideoEntities> getAllVideos() {
        return videoRepositories.findAll();
    }

    public Optional<VideoEntities> getVideoById(Long id) {
        return videoRepositories.findById(id);
    }

    public VideoEntities createVideo(VideoEntities video) {
        return videoRepositories.save(video);
    }

    public void deleteVideoById(Long id) {
        videoRepositories.deleteById(id);
    }
    public VideoEntities updateVideo(Long id, VideoEntities updatedVideo) {
        return videoRepositories.findById(id).map(
                existingVideo -> {
                    existingVideo.setTitle(updatedVideo.getTitle());
                    existingVideo.setDescription(updatedVideo.getDescription());
                    return videoRepositories.save(existingVideo);
                }).orElseThrow(() -> new RuntimeException("Video not found"));
    }
}
