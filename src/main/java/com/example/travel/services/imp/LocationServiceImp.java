package com.example.travel.services.imp;

import com.example.travel.daos.Hotel;
import com.example.travel.daos.Image;
import com.example.travel.daos.Location;
import com.example.travel.daos.Video;
import com.example.travel.dtos.LocationDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.ImageRepository;
import com.example.travel.repositories.LocationRepository;
import com.example.travel.repositories.VideoRepository;
import com.example.travel.services.ILocationService;
import com.example.travel.utils.Convert;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImp implements ILocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Location> getAllLocation() {
        System.out.println(1);
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()){
            throw new NotFoundException("No location");
        }
        System.out.println(2);
        return locations;
    }

    @Override
    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isEmpty()) {
            throw new NotFoundException("Cound not find a location with id: " + id);
        }
        return location.get();
    }

    @Override
    public Location createLocation(LocationDTO locationDTO) {
        System.out.println(0);
        Location oldLocation = locationRepository.findByName(locationDTO.getName());
        System.out.println(1);
        if (oldLocation != null) {
            throw new DuplicateRequestException("Location has already exists");
        }
        System.out.println(2);
        Location location = Convert.fromLocationDTOToLocation(locationDTO);

        System.out.println(3);
        return locationRepository.save(location);
    }

    @Override
    public Location editLocation(Long id, LocationDTO locationDTO) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isEmpty()) {
            throw new NotFoundException("Cound not find a location with id: " + id);
        }
        location.get().setName(locationDTO.getName());
        location.get().setAddress(locationDTO.getAddress());
        location.get().setDescription(locationDTO.getDescription());
        return locationRepository.save(location.get());
    }

    @Override
    public void deleteLocation(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isEmpty()) {
            throw new NotFoundException("Cound not find a location with id: " + id);
        }
        locationRepository.deleteById(id);
    }

    @Override
    public List<Image> getImgByLocationId(Long id) {
        List<Image> lists = locationRepository.findById(id).get().getImages();
        return lists;
    }

    @Override
    public List<Video> getVideoByLocationId(Long id) {
        List<Video> videos = locationRepository.findById(id).get().getVideos();
        return videos;
    }

    @Override
    public List<Hotel> getHotelByLocationId(Long id) {
        List<Hotel> hotels = locationRepository.findById(id).get().getHotels();
        return hotels;
    }


}
