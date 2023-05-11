package com.example.kursovaya;

import com.example.kursovaya.models.Footballer;
import com.example.kursovaya.repository.UserRepository;
import com.example.kursovaya.services.FootballerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class KursovayaApplicationTests {
    final FootballerService footballerService;

    KursovayaApplicationTests( FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @Test
    void contextLoads() {

        Footballer footballer=new Footballer(10,"Artem","Vlastyuk");
        footballerService.save(footballer);

    }

}
