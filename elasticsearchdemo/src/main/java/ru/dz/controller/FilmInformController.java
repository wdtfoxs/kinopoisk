package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.repository.MovieRepository;

/**
 * Created by Vlad.M on 08.12.2016.
 */
@Controller
public class FilmInformController {
    @Autowired
    MovieRepository movieRepository;
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String loadFilm(@PathVariable("id") Long id, ModelMap modelMap){
        modelMap.addAttribute("movie",movieRepository.findOne(id));
        return "movie";
    }
}
