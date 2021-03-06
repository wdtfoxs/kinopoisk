package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.elastic.MovieSearchService;
import ru.dz.entity.Movie;
import ru.dz.repository.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Created by Vlad.M on 07.12.2016.
 */
@Controller
public class MainPageController {
    private final MovieSearchService movieSearchService;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final HttpServletRequest request;
    private final PeopleRepository peopleRepository;
    private final AwardRepository awardRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public MainPageController(MovieSearchService movieSearchService, MovieRepository movieRepository,
                              RatingRepository ratingRepository, HttpServletRequest request,
                              PeopleRepository peopleRepository, AwardRepository awardRepository,
                              CountryRepository countryRepository) {
        this.movieSearchService = movieSearchService;
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.request = request;
        this.peopleRepository = peopleRepository;
        this.awardRepository = awardRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    private void init() {
        AutoCompite autoCompite = new AutoCompite();
        peopleRepository.save(autoCompite.loadPeopleFromJson());
        movieRepository.save(autoCompite.loadMoviesFromJson());
        countryRepository.save(autoCompite.loadCountryFromJson());
        awardRepository.save(autoCompite.createRelationships(movieRepository.findAll(), peopleRepository.findAll()));
        ArrayList<Movie> movies = (ArrayList<Movie>) movieRepository.findAll();
        for (Movie movy : movies) {
            Movie added = movieRepository.saveAndFlush(movy);
            movieSearchService.add(added);
        }
    }

    @IncludeUser
    @RequestMapping(value = {"/main", "/"}, method = RequestMethod.GET)
    public String loadpage() {
        return "main";
    }
}
