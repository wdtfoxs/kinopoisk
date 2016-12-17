package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.elastic.MovieSearchService;
import ru.dz.repository.AutoCompite;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.RatingRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Vlad.M on 07.12.2016.
 */
@Controller
public class MainPageController {
    private final MovieSearchService movieSearchService;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final HttpServletRequest request;

    @Autowired
    public MainPageController(MovieSearchService movieSearchService, MovieRepository movieRepository, RatingRepository ratingRepository, HttpServletRequest request) {
        this.movieSearchService = movieSearchService;
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.request = request;
    }

    @PostConstruct
    private void init() {
//        AutoCompite autoCompite = new AutoCompite();
//        movieRepository.save(autoCompite.loadMoviesFromJson());
//        ArrayList<Movie> movies = (ArrayList<Movie>) movieRepository.findAll();
//        for (Movie movy : movies) {
//            Movie added = movieRepository.saveAndFlush(movy);
//            movieSearchService.add(added);
//        }
    }

    @IncludeUser
    @RequestMapping(value = {"/main", "/"}, method = RequestMethod.GET)
    public String loadpage() {
        return "main";
    }
}
