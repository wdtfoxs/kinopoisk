package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import ru.springproject.core.dz.services.MovieService;
import ru.springproject.core.dz.services.ReviewService;
import ru.springproject.dz.security.MyUserDetail;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final HttpServletRequest request;
    private MovieService movieService;
    private ReviewService reviewService;

    @Autowired
    public MovieController(HttpServletRequest request, MovieService movieService, ReviewService reviewService) {
        this.request = request;
        this.movieService = movieService;
        this.reviewService = reviewService;
    }


    @RequestMapping(value = "{id}")
    public String getMovie(@PathVariable Long id){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof MyUserDetail){
            request.setAttribute("existReview", reviewService.existReview(id, ((MyUserDetail) user).getUser().getId()));
        }
        request.setAttribute("movie", movieService.getMovieById(id));
        return "movie";
    }
}
