package com.example.kursovaya.services;

import com.example.kursovaya.models.FootballUniform;
import com.example.kursovaya.models.Footballer;
import com.example.kursovaya.repository.FootballerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service

public class FootballerService {
    private final FootballerRepository footballerRepository;

    public FootballerService(FootballerRepository footballerRepository) {
        this.footballerRepository = footballerRepository;
    }
    public List<Footballer> getFootballersByNames(List<String> footballers){
        return footballerRepository.getFootballersByNames(footballers);
    }
    public void save(Footballer footballer) {
        footballerRepository.save(footballer);
    }
    public List<Footballer> getFootballers() {
        return footballerRepository.findAll();
    }
    public void deleteById(int id){
        Footballer footballer=footballerRepository.getFootballerById(id);
        for(FootballUniform uniform : footballer.getFootballUniforms()){
            uniform.deleteFootballer(footballer);
        }
        footballerRepository.save(footballer);
        footballerRepository.deleteById(id);
    }
    public Footballer getById(int id){
        return footballerRepository.getFootballerById(id);
    }

}
