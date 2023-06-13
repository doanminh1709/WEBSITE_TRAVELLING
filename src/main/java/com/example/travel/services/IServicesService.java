package com.example.travel.services;

import com.example.travel.daos.Comment;
import com.example.travel.daos.Services;
import com.example.travel.dtos.CommentDTO;
import com.example.travel.dtos.ServicesDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface IServicesService {
    List<Services> getAllService();
    Services getServiceById(Long id);
    Services createService(ServicesDTO serviceDTO, Long roomId,Long userId);
    Services editServiceById(Long id, ServicesDTO serviceDTO);
    void deleteServiceById(Long id);
    Comment createCmt(CommentDTO commentDTO, Long servicesId, MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException;

    List<Services> getAllServiceByIdRoom(Long idRoom);
    List<Services> getAllServiceByIdUser(Long idUser);
}
