package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.entity.Movie;
import ru.dz.repository.CountryRepository;
import ru.dz.repository.MovieRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping(value = "/add")
public class AddFilmController {

    private final CountryRepository countryRepository;
    private final HttpServletRequest request;
    private final MovieRepository movieRepository;

    @Autowired
    public AddFilmController(CountryRepository countryRepository, HttpServletRequest request, MovieRepository movieRepository) {
        this.countryRepository = countryRepository;
        this.request = request;
        this.movieRepository = movieRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(){
        request.setAttribute("country", countryRepository.findAll());
        return "addfilm";
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    public String addFilm(@RequestParam String name, @RequestParam String description,
                          @RequestParam int year, @RequestParam int age,
                          @RequestParam String image, @RequestParam String trailer,
                          @RequestParam String country){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setYear(year);
        movie.setAge(age);
        System.out.println(image);
        if (!Objects.equals(image, "")) {
            movie.setImage(image);
        }
        if (!Objects.equals(trailer, "")) {
            movie.setTrailer(trailer);
        }
        movie.setCountry(countryRepository.findOne(country));
        movieRepository.saveAndFlush(movie);
        return "redirect:/movie/" + movie.getId();
//        System.out.println(name +" : " + description + " : " +
//        year + " : " + age + " : " + image + " : " + trailer + " : " +
//        country);
    }
}
