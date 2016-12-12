package ru.springproject.core.dz.repositories;

import ru.springproject.core.dz.entity.Movie;
import ru.springproject.core.dz.entity.Review;
import ru.springproject.core.dz.entity.User;

public interface ReviewRepository {
    Review getReview(User user, Movie movie);


    void addReview(Review review);
}
