package ru.springproject.dz.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Year {

    @Id
    private int year;

    @OneToMany
    @JoinColumn(name = "movie_id")
    private List<Movie> movies;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
