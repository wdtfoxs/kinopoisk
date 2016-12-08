package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.elastic.MovieSearchService;
import ru.dz.entity.Movie;
import ru.dz.repository.MovieRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created by Vlad.M on 07.12.2016.
 */
@Controller
public class MainPageController {
    @Autowired
    MovieSearchService movieSearchService;
    @Autowired
    MovieRepository movieRepository;
    @PostConstruct
    private void init(){
        ArrayList<Movie> movies = (ArrayList<Movie>) movieRepository.findAll();
        for (Movie movy : movies) {
            movieSearchService.add(movy);
        }
    }
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String loadpage(){
        return "main";
    }
}
