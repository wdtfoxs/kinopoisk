package ru.springproject.core.dz.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(length = 4)
    private int year;

    @Column(length = 2)
    private int age;

    private String image = "../../resources/images/single.jpg";

    private String trailer;

    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "movies_actors",
           joinColumns = @JoinColumn(name = "movie_id"),
           inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<People> peoples;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REFRESH)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REFRESH)
    private List<Review> reviews;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
