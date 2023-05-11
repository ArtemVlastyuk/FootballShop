package com.example.kursovaya.services;

import com.example.kursovaya.models.ImageFootballUniform;
import com.example.kursovaya.repository.ImageFootballUniformRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@Component
@Transactional
public class ImageFootballUniformService {

    private final ImageFootballUniformRepository imageFootballUniformRepository;

    public ImageFootballUniformService(ImageFootballUniformRepository imageFootballUniformRepository) {
        this.imageFootballUniformRepository = imageFootballUniformRepository;
        //System.out.println(this.imageFootballUniformRepository);
    }


    public ImageFootballUniform getById(int id) {
        return imageFootballUniformRepository.getImageFootballUniformById(id);
    }
}
