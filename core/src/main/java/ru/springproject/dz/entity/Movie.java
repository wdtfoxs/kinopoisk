package ru.springproject.dz.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private int year;

    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres;

   @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;


}
