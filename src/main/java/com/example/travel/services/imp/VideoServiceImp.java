package com.example.travel.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.travel.daos.*;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.*;
import com.example.travel.services.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VideoServiceImp implements IVideoService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> getVideoById(Long id) {
        Optional<Video> videos = videoRepository.findById(id);
        if (videos.isEmpty()) {
            throw new NotFoundException("Video not found");
        }
        return (List<Video>) videos.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Video> createVideo(Long locationId, MultipartFile[] files) throws IOException {
        System.out.println(1);
        List<Video> videos = new ArrayList<>();
        System.out.println(2);
        Optional<Location> locations = locationRepository.findById(locationId);
        System.out.println(3);
        if (locations.isEmpty()) {
            throw new NotFoundException("Location not found");
        }
        System.out.println(4);
        Location location = locations.get();
        System.out.println(5);
        for (int i = 0; i < files.length; i++) {
            System.out.println(6);
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(files[i].getBytes(), ObjectUtils.emptyMap());
            System.out.println(7);
            Video video = new Video();
            System.out.println(8);
            video.setLocation(location);
            System.out.println(9);
            video.setLinkVideo(cloudinaryMap.get("secure_url").toString());
            System.out.println(10);
            video.setPublicId(cloudinaryMap.get("public_id").toString());
            System.out.println(11);
            Video newVideo = videoRepository.save(video);
            System.out.println(12);
            videos.add(newVideo);
        }
        System.out.println(13);
        return videos;
    }

    @Override
    public Video editVideo(Long videoId, MultipartFile[] files) {
        return null;
    }

    @Override
    public void deleteVideo(Long videoId) {
        Optional<Video> videos = videoRepository.findById(videoId);
        if (videos.isEmpty()) {
            throw new NotFoundException("Video not found");
        }
        videoRepository.deleteById(videoId);
    }
}
