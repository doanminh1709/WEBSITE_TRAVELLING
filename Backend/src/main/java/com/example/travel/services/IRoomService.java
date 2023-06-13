package com.example.travel.services;

import com.example.travel.daos.Image;
import com.example.travel.daos.Room;
import com.example.travel.dtos.RoomDTO;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    List<Room> getAllRoom();
    Optional<Room> getById(Long id);
    List<Image> getImgByRoomId(Long id);
    Room createRoom(RoomDTO roomDTO, Long hotelId);
    Room editRoomById(Long idHotel, Long idRoom, RoomDTO roomDTO);
    void deleteRoomById(Long idHotel, Long idRoom);
}
