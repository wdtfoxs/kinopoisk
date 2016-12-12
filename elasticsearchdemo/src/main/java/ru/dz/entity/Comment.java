package ru.dz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vlad.M on 13.12.2016.
 */
@Entity
public class Comment {
    public Comment() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private Date date;

    @ManyToOne

    @JoinColumn(name = "movie_fk")

    private Movie movie;

    @ManyToOne

    @JoinColumn(name = "user_fk")

    private User user;

    @Column(nullable = false)

    private String content;


    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;

    }


    public void setDate(Date date) {
        this.date = date;

    }


    public Movie getMovie() {
        return movie;

    }

    public void setMovie(Movie movie) {
        this.movie = movie;

    }

    public User getUser() {
        return user;

    }

    public void setUser(User user) {
        this.user = user;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;

    }


}
