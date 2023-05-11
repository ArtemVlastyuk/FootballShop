package com.example.kursovaya.repository;

import com.example.kursovaya.models.FootballUniform;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootballUniformRepository extends JpaRepository<FootballUniform,Long> {
    FootballUniform save(FootballUniform footballUniform);
    List<FootballUniform> findAll();
    FootballUniform getFootballUniformById(Long id);
}
