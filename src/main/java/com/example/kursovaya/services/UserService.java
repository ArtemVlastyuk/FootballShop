package com.example.kursovaya.services;

import com.example.kursovaya.models.FootballUniform;
import com.example.kursovaya.models.Role;
import com.example.kursovaya.models.User;
import com.example.kursovaya.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    public boolean createUser(User user){
        if(userRepository.findByEmail(user.getEmail())!=null){
            return false;
        }
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public User getByUserName(String username){
        return userRepository.findByEmail(username);
    }
    public void addFootballUniform(User user,FootballUniform footballUniform){
        //System.out.println("server");
        user.addFootballUniform(footballUniform);
        footballUniform.addUser(user);
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
