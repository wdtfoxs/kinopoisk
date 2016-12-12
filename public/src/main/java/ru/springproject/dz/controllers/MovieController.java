package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.springproject.core.dz.entity.Movie;
import ru.springproject.core.dz.services.MovieService;
import ru.springproject.core.dz.services.ReviewService;
import ru.springproject.dz.security.MyUserDetail;
import ru.springproject.dz.valid.RatingBean;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final HttpServletRequest request;
    private MovieService movieService;
    private ReviewService reviewService;
    private static final String RATING_FORM = "ratingForm";

    @Autowired
    public MovieController(HttpServletRequest request, MovieService movieService, ReviewService reviewService) {
        this.request = request;
        this.movieService = movieService;
        this.reviewService = reviewService;
    }


    @RequestMapping(value = "{id}")
    public String getMovie(@PathVariable Long id){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Movie movie = movieService.getMovieById(id);
        if (user instanceof MyUserDetail){
            request.setAttribute("existReview", reviewService.existReview(((MyUserDetail) user).getUser(), movie));
        }
        request.setAttribute("movie", movie);
        request.setAttribute(RATING_FORM, new RatingBean());
        return "movie";
    }
}
