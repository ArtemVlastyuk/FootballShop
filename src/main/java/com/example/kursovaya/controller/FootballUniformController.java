package com.example.kursovaya.controller;

import com.example.kursovaya.models.FootballUniform;
import com.example.kursovaya.services.FootballUniformService;
import com.example.kursovaya.services.FootballerService;
import com.example.kursovaya.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Component
@Transactional
@Controller
public class FootballUniformController {
    private final FootballUniformService footballUniformService;
    private final FootballerService footballerService;
    private final UserService userService;

    public FootballUniformController(FootballUniformService footballUniformService, FootballerService footballerService,
                                     UserService userService) {
        this.footballUniformService = footballUniformService;
        this.footballerService = footballerService;
        this.userService = userService;
    }

    @GetMapping("/footballUniforms")
    public String getFootballUniforms(Model model){
        model.addAttribute("footballUniform",footballUniformService.getFootballUniforms());
        return "footballUniforms";
    }
    @PostMapping("/footballUniforms")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveUniforms(@RequestParam(name = "footballers") List<String> footballers,
                               @RequestParam int price, @RequestParam String title, @RequestParam(required = false) List<String> sizes,
                               @RequestParam MultipartFile file1, @RequestParam MultipartFile file2) throws IOException {
        FootballUniform footballForm=new FootballUniform();
        footballForm.setFootballers(footballerService.getFootballersByNames(footballers));
        footballForm.setTitle(title);
        footballForm.setPrice(price);
        footballForm.setSizes(sizes);
        footballUniformService.saveFootballUniform(footballForm,file1,file2);
        return "redirect:/footballUniforms";
    }

    @GetMapping("/footballUniforms/add")
    public String setFootballUniform(Model model){
        model.addAttribute("footballers",footballerService.getFootballers());
        return "footballUniformAdd";
    }
    @PostMapping("/footballUniforms/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String addFootballUniformInCart(@PathVariable Long id,Principal principal){
        //System.out.println("сохранение");
        userService.addFootballUniform(userService.getByUserName(principal.getName()),
                footballUniformService.getFootballUniformById(id));
        return "redirect:/cart";
    }
    @DeleteMapping ("/footballUniforms/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteFootballUniform(@PathVariable Long id)
    {
        //System.out.println("Удаление формы");
        footballUniformService.deleteFootballUniformByID(id);
        return "redirect:/footballUniforms";
    }

    @GetMapping("/footballUniforms/{id}")
    public String getFootballUniform(@PathVariable String id, Principal principal, Model model){
//        footballUniformService.getFootballUniformById(Integer.parseInt(id));
        System.out.println(footballUniformService.getFootballUniformById(Long.parseLong(id)).getImages().size());
//
        if(principal==null){
            model.addAttribute("Admin",false);
        }
        else {
            model.addAttribute("Admin", userService.getByUserName(principal.getName()).isAdmin());
        }
        model.addAttribute("footballForm",
                footballUniformService.getFootballUniformById(Long.parseLong(id)));

        return "footballUniform";
    }
}
