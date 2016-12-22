package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.entity.Movie;
import ru.dz.entity.People;
import ru.dz.repository.CountryRepository;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.PeopleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/add")
public class AddFilmController {

    private final CountryRepository countryRepository;
    private final HttpServletRequest request;
    private final MovieRepository movieRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public AddFilmController(CountryRepository countryRepository, HttpServletRequest request, MovieRepository movieRepository, PeopleRepository peopleRepository) {
        this.countryRepository = countryRepository;
        this.request = request;
        this.movieRepository = movieRepository;
        this.peopleRepository = peopleRepository;
    }

    @IncludeUser
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
        request.getSession().setAttribute("movie", movie);
        return "redirect:/movie/" + movie.getId();
//        return "redirect:/actor";
//        System.out.println(name +" : " + description + " : " +
//        year + " : " + age + " : " + image + " : " + trailer + " : " +
//        country);
    }

    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public String addActorToFilm(){
        if (request.getSession().getAttribute("movie") != null){
            request.setAttribute("actors", peopleRepository.findAll());
            return "addActorToFilm";
        } else {
            return "redirect:/add";
        }
    }

    @RequestMapping(value = "/actor/add", method = RequestMethod.POST)
    public String addActor(@RequestParam Long id){
        List actors = (List) request.getAttribute("actors");
        People actor = peopleRepository.findOne(id);
        actors.remove(actor);
        request.setAttribute("actors", actors);
        return "kek";
    }
}
