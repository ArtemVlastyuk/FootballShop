package com.example.kursovaya.services;

import com.example.kursovaya.models.FootballUniform;
import com.example.kursovaya.models.Footballer;
import com.example.kursovaya.models.ImageFootballUniform;
import com.example.kursovaya.models.User;
import com.example.kursovaya.repository.FootballUniformRepository;
import com.example.kursovaya.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Component
public class FootballUniformService {

    private final FootballUniformRepository footballUniformRepository;
    private final UserRepository userRepository;

    public FootballUniformService(FootballUniformRepository footballUniformRepository, UserRepository userRepository) {
        this.footballUniformRepository = footballUniformRepository;
        this.userRepository = userRepository;
    }
    public void deleteFootballUniformByID(Long id){
        FootballUniform footballUniform=footballUniformRepository.getFootballUniformById(id);
        footballUniform.setFootballers(null);
        System.out.println(footballUniform.getUsers().size());
        for(User user : footballUniform.getUsers()){
            user.deleteFootballUniform(footballUniform);
            userRepository.save(user);
        }
        footballUniformRepository.save(footballUniform);
        footballUniformRepository.deleteById(id);
    }
    public void deleteUser(User user, FootballUniform footballUniform){
        footballUniform.deleteUser(user);
        footballUniformRepository.save(footballUniform);
    }
    public void saveFootballUniform(FootballUniform footballUniform, MultipartFile file1, MultipartFile file2) throws IOException {
        if(file1.getSize()!=0) {
            ImageFootballUniform imageFootballUniform = toImageEntity(file1);
            imageFootballUniform.setPreviewImage(true);
            footballUniform.addImageToProduct(imageFootballUniform);
        }
        if(file2.getSize()!=0){
            ImageFootballUniform imageFootballUniform2=toImageEntity(file2);
            footballUniform.addImageToProduct(imageFootballUniform2);
        }

        footballUniformRepository.save(footballUniform);


    }
    public FootballUniform getFootballUniformById(Long id){
        return footballUniformRepository.getFootballUniformById(id);
    }
    private ImageFootballUniform toImageEntity(MultipartFile file) throws IOException {
        ImageFootballUniform image = new ImageFootballUniform();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public List<FootballUniform> getFootballUniforms(){
        return footballUniformRepository.findAll();
    }
}
