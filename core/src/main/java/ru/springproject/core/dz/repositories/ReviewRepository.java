package ru.springproject.core.dz.repositories;

import ru.springproject.core.dz.entity.Review;

public interface ReviewRepository {
    Review getReview(Long movieId, Long userId);

    void addReview(Review review);
}
