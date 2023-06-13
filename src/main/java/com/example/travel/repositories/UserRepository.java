package com.example.travel.repositories;

import com.example.travel.daos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
    User findUserByUsernameAndPassword(String username, String password);
}
