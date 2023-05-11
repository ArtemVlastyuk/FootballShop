package com.example.kursovaya.controller;

import com.example.kursovaya.models.User;
import com.example.kursovaya.services.FootballUniformService;
import com.example.kursovaya.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final FootballUniformService footballUniformService;


    public UserController(UserService userService, FootballUniformService footballUniformService) {
        this.userService = userService;
        this.footballUniformService = footballUniformService;
    }
    @GetMapping("/registration")
    public String registration(){
//        System.out.println("allee");

        return "registration";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/registration")
    public String createUser(@RequestParam String firstname, @RequestParam String lastname,
                             @RequestParam String email, @RequestParam String password, Model model) {
       // System.out.println("reg");
        User user=new User();
        user.setPassword(password);
        user.setUsername(email);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/profil")
    public String getProfil(Principal principal, Model model){
        model.addAttribute("user",userService.getByUserName(principal.getName()));
        //System.out.println(principal.getName());
        return "profil";
    }
    @GetMapping("/")
    public String getHome(Principal principal, Model model){
        if(principal==null){
            model.addAttribute("Admin",false);
        }
        else {
            model.addAttribute("Admin", userService.getByUserName(principal.getName()).isAdmin());
        }
        return "index";
    }
    @GetMapping("/cart")
    public String getCart(Principal principal,Model model){
        model.addAttribute("user",userService.getByUserName(principal.getName()));
        // System.out.println(userService.getByUserName(principal.getName()).getFootballUniforms().size());
        return "cart";
    }
    @DeleteMapping("/cart/{id}")
    public String getCart(@PathVariable Long id, Principal principal, Model model){
        //System.out.println("Удаление");
        footballUniformService.deleteUser(userService.getByUserName(principal.getName()),
                footballUniformService.getFootballUniformById(id));
        return "redirect:/cart";
    }
}
