package com.example.kursovaya.controller;

import com.example.kursovaya.models.Footballer;
import com.example.kursovaya.repository.UserRepository;
import com.example.kursovaya.services.FootballerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Component
@Transactional
@Controller
public class FootballerController {
    private final FootballerService footballerService;
    private final UserRepository userRepository;
    public FootballerController(FootballerService footballerService, UserRepository userRepository) {
        this.footballerService = footballerService;
        this.userRepository = userRepository;
    }
    @DeleteMapping("/footballers/{id}")

    public String deleteFootballUniform(@PathVariable int id)
    {
//        System.out.println("Удаление");
        footballerService.deleteById(id);
        return "redirect:/footballers";
    }
    @GetMapping("/footballers")
    public String getFootballer(Model model){
       // System.out.println(footballerService.getFootballers().size());
        model.addAttribute("footballers",footballerService.getFootballers());
        return "footballers";

    }
    @GetMapping("/footballers/{id}")
    public String getFootballer(@PathVariable int id, Model model, Principal principal){

        model.addAttribute("footballer",footballerService.getById(id));
        model.addAttribute("Admin",userRepository.findByEmail(principal.getName()).isAdmin());

        return "footballer";

    }
    @GetMapping("/footballers/add")
    public String AddFootballer(){
        return "footballerAdd";
    }
    @PostMapping("/footballers")
    public String saveFootballer(@RequestParam String firstname,@RequestParam String lastname,@RequestParam int number){
        footballerService.save(new Footballer(number,firstname,lastname));
        return "redirect:/footballers";
    }
}
