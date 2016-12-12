package ru.dz.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
/**
 * Created by Vlad.M on 07.12.2016.
 */
@Entity
public class Rating {
    public Rating(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonBackReference
    @ManyToOne
    @JoinTable(name = "rated_movies")
    private Movie movie;
    @OneToOne
    private MyUser user;
    private Integer rating;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
