package ru.kpfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.entity.Movie;
import ru.kpfu.service.MovieService;

/**
 * Created by Vlad.M on 23.11.2016.
 */
@Controller
public class MovieController {
    @Autowired
    MovieService movieService;
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String loadPage(){
        return "movie";
    }
    @ResponseBody
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String addMovie(@RequestParam("name") String name, @RequestParam("descr") String description){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movieService.add(movie);
        return movieService.matchPhrasePrefixQuery("").get(0).getName();
    }
}
