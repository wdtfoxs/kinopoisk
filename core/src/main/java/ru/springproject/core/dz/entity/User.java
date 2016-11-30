package ru.springproject.core.dz.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(length = 40)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(length = 5)
    private ru.springproject.core.dz.entity.UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ru.springproject.core.dz.entity.UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(ru.springproject.core.dz.entity.UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

