package com.example.kursovaya.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "footballer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Footballer {
    @Id
    private int id;
    @Column(name= "firstname")
    private String firstname;
    @Column(name= "lastname")
    private String lastname;
    @ManyToMany(mappedBy = "footballers")
    private List<FootballUniform> footballUniforms=new ArrayList<>();
    public Footballer(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
