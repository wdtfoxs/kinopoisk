package ru.springproject.core.dz.entity;

import ru.springproject.core.dz.entity.enums.WhoInFilm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String patronymic;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private WhoInFilm whoInFilm;

    @ManyToMany(mappedBy = "peoples")
    private List<Movie> movies;

    @ManyToMany(mappedBy = "peoples")
    private List<Award> awards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public WhoInFilm getWhoInFilm() {
        return whoInFilm;
    }

    public void setWhoInFilm(WhoInFilm whoInFilm) {
        this.whoInFilm = whoInFilm;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
}
