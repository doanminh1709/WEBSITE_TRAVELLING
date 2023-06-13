package com.example.travel.utils;

import com.example.travel.daos.*;
import com.example.travel.dtos.*;

public class Convert {
    public static User fromUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setDateOfBirth(userDTO.getDateOfBirth());

        return user;
    }

    public static Location fromLocationDTOToLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        location.setDescription(locationDTO.getDescription());

        return location;
    }

    public static Hotel fromHotelDTOToHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setPhoneNumber(hotelDTO.getPhoneNumber());
        hotel.setDescription(hotelDTO.getDescription());

        return hotel;
    }

    public static Room fromRoomDTOToRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setPrice(roomDTO.getPrice());
        room.setType(roomDTO.getType());
        room.setDescription(roomDTO.getDescription());

        return room;
    }

    public static Comment fromCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setRate(commentDTO.getRate());

        return comment;
    }

    public static Services fromServicesDTOToService(ServicesDTO servicesDTO) {
        Services services = new Services();
        services.setStartedTime(servicesDTO.getStartedTime());
        services.setEndedTime(servicesDTO.getEndedTime());
        services.setNumberOfPeople(servicesDTO.getNumberOfPeople());
        services.setPromotion(servicesDTO.getPromotion());

        return services;
    }

    public static Contact fromContactDTOToContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setDescription(contactDTO.getDescription());

        return contact;
    }
}
