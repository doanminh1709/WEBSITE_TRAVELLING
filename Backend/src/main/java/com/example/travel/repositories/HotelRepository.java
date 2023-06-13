package com.example.travel.repositories;

import com.example.travel.daos.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByName(String name);
    Optional<Hotel> findById(Long id);
}
