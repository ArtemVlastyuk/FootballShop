package com.example.kursovaya.controller;

import com.example.kursovaya.models.ImageFootballUniform;
import com.example.kursovaya.services.ImageFootballUniformService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@Component

public class ImageController {

    private final ImageFootballUniformService imageFootballUniformService;

    public ImageController(ImageFootballUniformService imageFootballUniformService) {
        this.imageFootballUniformService = imageFootballUniformService;
    }

    @GetMapping("/images/{id}")

    private ResponseEntity<?> getImageById(@PathVariable("id") int id) {
        //System.out.println(this.imageFootballUniformService);
        ImageFootballUniform image = imageFootballUniformService.getById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
