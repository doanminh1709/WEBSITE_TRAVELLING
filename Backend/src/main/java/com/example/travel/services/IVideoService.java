package com.example.travel.services;

import com.example.travel.daos.Image;
import com.example.travel.daos.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IVideoService {
    List<Video> getAllVideo();
    List<Video> getVideoById(Long id);
    List<Video> createVideo(Long locationId, MultipartFile[] files) throws IOException;
    Video editVideo(Long videoId, MultipartFile[] files);
    void deleteVideo(Long videoId);
}
