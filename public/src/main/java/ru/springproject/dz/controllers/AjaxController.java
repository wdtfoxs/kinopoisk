package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.springproject.core.dz.entity.Movie;
import ru.springproject.core.dz.repositories.HiberMovie;

import java.util.List;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private HiberMovie hiberMovie;

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(){
        return "page";
    }


    @ResponseBody
    @RequestMapping(value = "/getMovies", method = RequestMethod.GET)
    public List<Movie> getMovie(String text){
        return hiberMovie.getMovie(text);
    }
}
