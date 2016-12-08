package ru.dz.entity;
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
    @ManyToOne
    @JoinTable(name = "rated_movies")
    private Movie movie;
    @OneToOne
    private MyUser user;
    private Float rating;


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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
