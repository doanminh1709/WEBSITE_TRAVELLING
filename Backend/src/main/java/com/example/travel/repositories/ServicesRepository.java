package com.example.travel.repositories;

import com.example.travel.daos.Room;
import com.example.travel.daos.Services;
import com.example.travel.daos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {


    @Query("select s from Services s where s.room = ?1")
    List<Services> findAllByRoom(Room room);

    @Query("select s from Services s where s.user = ?1")
    List<Services> findAllByUser(User user);
}
