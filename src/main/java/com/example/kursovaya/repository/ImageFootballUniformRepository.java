package com.example.kursovaya.repository;

import com.example.kursovaya.models.FootballUniform;
import com.example.kursovaya.models.ImageFootballUniform;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageFootballUniformRepository extends JpaRepository<ImageFootballUniform,Long> {
    ImageFootballUniform getImageFootballUniformById(int id);
}
