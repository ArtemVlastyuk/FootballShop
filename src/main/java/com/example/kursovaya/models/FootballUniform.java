package com.example.kursovaya.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "football_uniform")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FootballUniform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name="price")
    private double price;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Footballer> footballers;
    @ElementCollection
    // @JoinColumn(name = "sizes")
    private List<String> sizes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "product")
    private List<ImageFootballUniform> images = new ArrayList<>();

    @ManyToMany()
    private Set<User> users=new HashSet<>();

    public void deleteFootballer(Footballer footballer){
        footballers.remove(footballer);
    }
    public void deleteUser(User user){
        users.remove(user);
    }
    public void addUser(User user){
        users.add(user);
    }
    public void addImageToProduct(ImageFootballUniform image) {
        image.setProduct(this);
        images.add(image);
    }
}
