package com.example.travel.services;

import com.example.travel.daos.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    List<Image> getAllImage();

    Image getImageById(Long id);

    List<Image> createImgLocation(Long locationId, MultipartFile[] files) throws IOException;
    List<Image> createImgHotel(Long locationId, Long hotelId, MultipartFile[] files) throws IOException;
    List<Image> createImgRoom(Long locationId, Long hotelId, Long roomId, MultipartFile[] files) throws IOException;

    void deleteImg(Long id) throws IOException;
}
