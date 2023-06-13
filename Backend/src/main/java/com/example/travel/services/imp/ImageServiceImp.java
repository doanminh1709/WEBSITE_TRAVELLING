package com.example.travel.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.travel.daos.Hotel;
import com.example.travel.daos.Image;
import com.example.travel.daos.Location;
import com.example.travel.daos.Room;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.HotelRepository;
import com.example.travel.repositories.ImageRepository;
import com.example.travel.repositories.LocationRepository;
import com.example.travel.repositories.RoomRepository;
import com.example.travel.services.IImageService;
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
public class ImageServiceImp implements IImageService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(Long id) {
        Optional<Image> images = imageRepository.findById(id);
        if (images.isEmpty()) {
            throw new NotFoundException("Image not found");
        }
        return images.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Image> createImgLocation(Long locationId, MultipartFile[] files) throws IOException {
        List<Image> images = new ArrayList<>();
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {
            throw new NotFoundException("Location not found");
        }
        for (MultipartFile file : files) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setLocation(location.get());
            image.setLinkImg(cloudinaryMap.get("secure_url").toString());
            image.setPublicId(cloudinaryMap.get("public_id").toString());
            Image newImg = imageRepository.save(image);
            images.add(newImg);
        }
        return images;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Image> createImgHotel(Long locationId, Long hotelId, MultipartFile[] files) throws IOException {
        List<Image> images = new ArrayList<>();
        Optional<Location> location = locationRepository.findById(locationId);
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (location.isEmpty() || hotel.isEmpty()) {
            throw new NotFoundException("Location or Hotel not found");
        }
        for (MultipartFile file : files) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setLocation(location.get());
            image.setHotel(hotel.get());
            image.setLinkImg(cloudinaryMap.get("secure_url").toString());
            image.setPublicId(cloudinaryMap.get("public_id").toString());
            Image newImg = imageRepository.save(image);
            images.add(newImg);
        }
        return images;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Image> createImgRoom(Long locationId, Long hotelId, Long roomId, MultipartFile[] files) throws IOException {
        List<Image> images = new ArrayList<>();
        Optional<Location> location = locationRepository.findById(locationId);
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        Optional<Room> room = roomRepository.findById(roomId);
        if (location.isEmpty() ||
                hotel.isEmpty() ||
                room.isEmpty()) {
            throw new NotFoundException("Location or Hotel or Room not found");
        }
        for (MultipartFile file : files) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setLocation(location.get());
            image.setHotel(hotel.get());
            image.setRoom(room.get());
            image.setLinkImg(cloudinaryMap.get("secure_url").toString());
            image.setPublicId(cloudinaryMap.get("public_id").toString());
            Image newImg = imageRepository.save(image);
            images.add(newImg);
        }

        return images;
    }

    @Override
    public void deleteImg(Long id) throws IOException {
        Optional<Image> images = imageRepository.findById(id);
        if (images.isEmpty()) {
            throw new NotFoundException("Image not found");
        }
        cloudinary.uploader().destroy(images.get().getPublicId(), ObjectUtils.emptyMap());
        imageRepository.deleteById(id);
    }
}
