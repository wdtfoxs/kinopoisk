package ru.dz.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Vlad.M on 23.11.2016.
 */
@Entity
public class Movie implements MyObject{
    public Movie(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "movie_workers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> workers;
    private Integer rating_num;
    private Date date;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private Set<Genre> genres;
    @Column(length = 2048)
    private String description;
    private String country;
    private Integer voted_number;
    @ManyToMany
    @JoinTable(name = "movie_awards",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "award_id"))
    private List<Award> awards;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<Comment> comments;
    @JsonManagedReference
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;
    private String image;
    @Transient
    private Integer isVoted;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Person> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Person> workers) {
        this.workers = workers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getVoted_number() {
        return voted_number;
    }

    public void setVoted_number(Integer voted_number) {
        this.voted_number = voted_number;
    }

    public Integer getRating_num() {
        return rating_num;
    }

    public void setRating_num(Integer rating_num) {
        this.rating_num = rating_num;
    }

    public Integer getIsVoted() {
        return isVoted;
    }

    public void setIsVoted(Integer isVoted) {
        this.isVoted = isVoted;
    }
    @Transient
    public Integer getRealRating(){
        return Math.round(this.getRating_num()/this.getVoted_number());
    }
    public String getAbsoluteRating(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(this.getRating_num()/this.getVoted_number());
    }

}
