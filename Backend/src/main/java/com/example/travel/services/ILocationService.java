package com.example.travel.services;

import com.example.travel.daos.Hotel;
import com.example.travel.daos.Image;
import com.example.travel.daos.Location;
import com.example.travel.daos.Video;
import com.example.travel.dtos.LocationDTO;

import java.util.List;

public interface ILocationService {
    List<Location> getAllLocation();
    Location getLocationById(Long id);
    Location createLocation(LocationDTO locationDTO);
    Location editLocation(Long id, LocationDTO locationDTO);
    void deleteLocation(Long id);
    List<Image> getImgByLocationId(Long id);
    List<Video> getVideoByLocationId(Long id);
    List<Hotel> getHotelByLocationId(Long id);
}
