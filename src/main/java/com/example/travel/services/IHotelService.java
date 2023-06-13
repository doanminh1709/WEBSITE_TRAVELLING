package com.example.travel.services;

import com.example.travel.daos.Hotel;
import com.example.travel.daos.Image;
import com.example.travel.daos.Room;
import com.example.travel.dtos.HotelDTO;
import com.example.travel.models.HotelResponse;

import java.util.List;
import java.util.Optional;

public interface IHotelService {
    List<Hotel> getAllHotel();
    HotelResponse getHotelById(Long id);
    Hotel createHotel(HotelDTO hotelDTO, Long locationId);
    Hotel editHotelById(Long id, HotelDTO hotelDTO);
    void deleteHotelById(Long id);
    List<Image> getImgByHotelId(Long id);
    List<Room> getRoomByHotelId(Long id);
}
