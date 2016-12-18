package ru.dz.entity;

import ru.dz.entity.enums.UserRole;
import ru.dz.entity.interfaces.MyObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements MyObject{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, unique = true)
    private String username;

    @Column(length = 40)
    private String password;

    @Column(unique = true)
    private String email;

    private Integer vk;

    private String photo = "/resources/images/p1.png";

    @Column(length = 16)
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.ROLE_USER;

    //TODO: these films are liked by user and can be viewed in /cabinet
    @ManyToMany
    @JoinTable(name = "user_liked_films",
            joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Movie> movies;

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getVk() {
        return vk;
    }

    public void setVk(Integer vk) {
        this.vk = vk;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

