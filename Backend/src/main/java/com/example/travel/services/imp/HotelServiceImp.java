package com.example.travel.services.imp;

import com.example.travel.daos.*;
import com.example.travel.dtos.HotelDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.models.HotelResponse;
import com.example.travel.repositories.*;
import com.example.travel.services.IHotelService;
import com.example.travel.utils.Convert;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HotelServiceImp implements IHotelService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CommentRepository commentRepository;



    @Override
    public List<Hotel> getAllHotel() {
//        List<Hotel> hotels = hotelRepository.findAll();
//        List<HotelResponse> hotelResponses = new ArrayList<>();
//        for (Hotel hotel : hotels) {
//            HotelResponse hotelResponse = new HotelResponse();
//            hotelResponse.setName(hotel.getName());
//            hotelResponse.setAddress(hotel.getAddress());
//            hotelResponse.setPhoneNumber(hotel.getPhoneNumber());
//            hotelResponse.setDescription(hotel.getDescription());
//            hotelResponse.setAvg(getAvgRateByHotelId(hotel.getId()));
//
//            hotelResponses.add(hotelResponse);
//        }

        return hotelRepository.findAll();
    }

    @Override
    public HotelResponse getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty()) {
           throw new NotFoundException("Cound not find a hotel with id: " + id);
        }
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setName(hotel.get().getName());
        hotelResponse.setAddress(hotel.get().getAddress());
        hotelResponse.setDescription(hotel.get().getDescription());
        hotelResponse.setPhoneNumber(hotel.get().getPhoneNumber());
        hotelResponse.setAvg(getAvgRateByHotelId(id));

        return hotelResponse;
    }

    @Override
    public Hotel createHotel(HotelDTO hotelDTO, Long locationId) {
        Optional<Location> oldLocation = locationRepository.findById(locationId);
        if(oldLocation.isEmpty()) {
            throw new NotFoundException("Cound not find a location with id: " + locationId);
        }
        Hotel oldHotel = hotelRepository.findByName(hotelDTO.getName());
        if (oldHotel != null) {
            throw new DuplicateRequestException("Hotel has already axists");
        }

        Hotel hotel = Convert.fromHotelDTOToHotel(hotelDTO);
        hotel.setLocation(oldLocation.get());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel editHotelById(Long id, HotelDTO hotelDTO) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty()) {
            throw new NotFoundException("Cound not find a hotel with id: " + id);
        }
        Hotel newHotel = hotel.get();
        newHotel = Convert.fromHotelDTOToHotel(hotelDTO);
        return hotelRepository.save(newHotel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty()) {
            throw new NotFoundException("Cound not find a hotel with id: " + id);
        }
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Image> getImgByHotelId(Long id) {
        List<Image> images = hotelRepository.findById(id).get().getImages();
        return images;
    }

    @Override
    public List<Room> getRoomByHotelId(Long id) {
        List<Room> rooms = hotelRepository.findById(id).get().getRooms();
        return rooms;
    }

    public double getAvgRateByHotelId(Long id) {
        double avg = 0, sum = 0;
        int i = 0;

        List<Comment> comments = commentRepository.findAll();
        for (Comment comment : comments) {
            Long hotelId = comment.getServices().getRoom().getHotel().getId();
            if(Objects.equals(hotelId, id)) {
                sum += comment.getRate();
                i++;
            }
        }
        if (i == 0) {
            avg = 0;
        }
        avg = (double) sum/i;

//        List<Comment> comments = commentRepository.findAll();
//        for (Comment comment : comments) {
//            Long locationId = comment.getServices().getLocation().getId();
//            List<Hotel> hotels = locationRepository.findById(locationId).get().getHotels();
//            for (Hotel hotel : hotels) {
//                if (hotel.getId() == id) {
//                    sum += comment.getRate();
//                    i++;
//                }
//            }
//        }
//        if (i == 0) {
//            avg = 0;
//        }
//        avg = (double) sum/i;

        return avg;
    }

}
