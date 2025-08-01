package org.learningspringwithduc.videoservice.services;

import org.learningspringwithduc.videoservice.entities.VideoEntities;
import org.learningspringwithduc.videoservice.repositories.VideoRepositories;
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

    public void createVideo(VideoEntities video) {
        videoRepositories.save(video);
    }

    public void deleteVideoById(Long id) {
        videoRepositories.deleteById(id);
    }

    public List<VideoEntities> getAllVideosById(List<Long> ids) {
        return videoRepositories.findAllById(ids);
    }

//    public VideoEntities updateVideo(Long id, VideoEntities updatedVideo) {
//        return videoRepositories.findById(id).map(
//                existingVideo -> {
//                    existingVideo.setTitle(updatedVideo.getTitle());
//                    existingVideo.setDescription(updatedVideo.getDescription());
//                    return videoRepositories.save(existingVideo);
//                }).orElseThrow(() -> new RuntimeException("Video not found"));
//    }
}
