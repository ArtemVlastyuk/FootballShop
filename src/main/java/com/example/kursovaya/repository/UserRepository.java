package com.example.kursovaya.repository;

import com.example.kursovaya.models.FootballUniform;
import com.example.kursovaya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Optional<User> findByUsername(String email);


}
