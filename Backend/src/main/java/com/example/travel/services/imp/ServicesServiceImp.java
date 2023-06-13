package com.example.travel.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.travel.daos.*;
import com.example.travel.dtos.CommentDTO;
import com.example.travel.dtos.ServicesDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.*;
import com.example.travel.services.IServicesService;
import com.example.travel.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ServicesServiceImp implements IServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public List<Services> getAllService() {
        return servicesRepository.findAll();
    }

    @Override
    public Services getServiceById(Long id) {
        Optional<Services> optional = servicesRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Could not find a service with id: "+ id);
        }
        return optional.get();
    }

    @Override
    public Services createService(ServicesDTO serviceDTO, Long roomId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new NotFoundException("No user");
        }
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()){
            throw new NotFoundException("No room");
        }
        Services services = Convert.fromServicesDTOToService(serviceDTO);
        services.setUser(user.get());
        services.setRoom(room.get());
        return servicesRepository.save(services);
    }


    @Override
    public Services editServiceById(Long id, ServicesDTO serviceDTO) {
        Optional<Services> optional = servicesRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Could not find a service with id: "+ id);
        }
        Services services = Convert.fromServicesDTOToService(serviceDTO);
        return servicesRepository.save(services);
    }

    @Override
    public void deleteServiceById(Long id) {
        Optional<Services> optional = servicesRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Could not find a service with id: "+ id);
        }
        servicesRepository.deleteById(id);
    }

    @Override
    public Comment createCmt(CommentDTO commentDTO, Long servicesId, MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException {
        User requestedUser = (User) httpServletRequest.getAttribute("user");
        Optional<Services> services = servicesRepository.findById(servicesId);
        if (services.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        User user = userRepository.findByUsername(requestedUser.getUsername());
        Comment comment = Convert.fromCommentDTOToComment(commentDTO);
        comment.setServices(services.get());
        comment.setUser(user);

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setComment(comment);
            image.setLinkImg(cloudinaryMap.get("secure_url").toString());
            image.setPublicId(cloudinaryMap.get("public_id").toString());
            Image newImg = imageRepository.save(image);
            images.add(newImg);
        }
        comment.setImages(images);

        return commentRepository.save(comment);
    }

    @Override
    public List<Services> getAllServiceByIdRoom(Long idRoom) {
        Optional<Room> room = roomRepository.findById(idRoom);
        if (room.isEmpty()){
            throw new NotFoundException("No room");
        }
        List<Services> services = servicesRepository.findAllByRoom(room.get());
        if (services.isEmpty()){
            throw new NotFoundException("No service");
        }
        return services;
    }

    @Override
    public List<Services> getAllServiceByIdUser(Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()){
            throw new NotFoundException("No user");
        }
        List<Services> services = servicesRepository.findAllByUser(user.get());
        if (services.isEmpty()){
            throw new NotFoundException("No service");
        }
        return services;
    }

}
