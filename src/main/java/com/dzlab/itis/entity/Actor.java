package com.dzlab.itis.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actors_movies",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;
}
