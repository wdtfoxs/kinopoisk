package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.springproject.core.dz.services.MovieService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private final HttpServletRequest request;
    private MovieService movieService;

    @Autowired
    public MovieController(HttpServletRequest request, MovieService movieService) {
        this.request = request;
        this.movieService = movieService;
    }


    @RequestMapping(value = "{id}")
    public String getMovie(@PathVariable Long id){
        request.setAttribute("movie", movieService.getMovieById(id));
        return "movie";
    }
}
