package ru.springproject.core.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springproject.core.dz.entity.Review;
import ru.springproject.core.dz.repositories.ReviewRepository;
import java.util.Date;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final MovieService movieService;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserService userService, MovieService movieService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.movieService = movieService;
    }

    @Transactional
    public boolean existReview(Long movieId, Long userId) {
        return !(reviewRepository.getReview(movieId, userId) == null);
    }

    @Transactional
    public void addReview(String review, Long userId, Long movieId) {
        Review rev = new Review();
        rev.setReview(review);
        rev.setUser(userService.getUserById(userId));
        rev.setMovie(movieService.getMovieById(movieId));
        rev.setDate(new Date());
        reviewRepository.addReview(rev);
    }
}
