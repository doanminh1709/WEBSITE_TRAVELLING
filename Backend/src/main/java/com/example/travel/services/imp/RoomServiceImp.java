package com.example.travel.services.imp;

import com.example.travel.daos.Hotel;
import com.example.travel.daos.Image;
import com.example.travel.daos.Room;
import com.example.travel.dtos.RoomDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.HotelRepository;
import com.example.travel.repositories.RoomRepository;
import com.example.travel.services.IRoomService;
import com.example.travel.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImp implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Image> getImgByRoomId(Long id) {
        List<Image> images = roomRepository.findById(id).get().getImages();
        return images;
    }

    @Override
    public Room createRoom(RoomDTO roomDTO, Long hotelId) {
        System.out.println(1);
        Optional<Hotel> oldHotel = hotelRepository.findById(hotelId);
        if (oldHotel.isEmpty()) {
            throw new NotFoundException("Hotel not found");
        }
        System.out.println(2);
        Room room = Convert.fromRoomDTOToRoom(roomDTO);
        System.out.println(3);
        room.setHotel(oldHotel.get());
        System.out.println(4);
        return roomRepository.save(room);
    }

    @Override
    public Room editRoomById(Long idHotel, Long idRoom, RoomDTO roomDTO) {
        Optional<Hotel> oldHotel = hotelRepository.findById(idHotel);
        if (oldHotel.isEmpty()) {
            throw new NotFoundException("Cound not find a hotel with id: " + idHotel);
        }

        Optional<Room> oldRoom = roomRepository.findById(idRoom);
        if (oldRoom.isEmpty()) {
            throw new NotFoundException("Cound not find a room with id: " + idHotel);
        }
        Room room = Convert.fromRoomDTOToRoom(roomDTO);
        room.setHotel(oldHotel.get());
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Long idHotel, Long idRoom) {
        Optional<Hotel> oldHotel= hotelRepository.findById(idHotel);
        if (oldHotel.isEmpty()) {
            throw new NotFoundException("Cound not find a hotel with id: " + idHotel);
        }

        Optional<Room> oldRoom = roomRepository.findById(idRoom);
        if (oldRoom.isEmpty()) {
            throw new NotFoundException("Cound not find a room with id: " + idHotel);
        }

        roomRepository.deleteById(idRoom);
    }
}
