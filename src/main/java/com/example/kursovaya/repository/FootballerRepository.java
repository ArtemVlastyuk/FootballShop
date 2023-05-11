package com.example.kursovaya.repository;

import com.example.kursovaya.models.Footballer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FootballerRepository extends JpaRepository<Footballer,Long> {
    List<Footballer> findAll();
    Footballer save(Footballer footballer);
    @Query(value = "select footballer.* from footballer where footballer.lastname in (:footballers)",nativeQuery = true)
    List<Footballer> getFootballersByNames(List<String> footballers);
    Footballer getFootballerById(int id);
    void deleteById(int id);
}
