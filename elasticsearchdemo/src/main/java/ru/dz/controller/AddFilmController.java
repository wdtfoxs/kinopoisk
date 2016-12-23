package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.elastic.MovieSearchService;
import ru.dz.entity.Movie;
import ru.dz.entity.People;
import ru.dz.entity.enums.Role;
import ru.dz.repository.CountryRepository;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.PeopleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    @Autowired
    MovieSearchService movieSearchService;

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
        movieSearchService.add(movieRepository.saveAndFlush(movie));
        request.getSession().setAttribute("movie", movie);
//        return "redirect:/movie/" + movie.getId();
        return "redirect:/add/actor";
//        System.out.println(name +" : " + description + " : " +
//        year + " : " + age + " : " + image + " : " + trailer + " : " +
//        country);
    }

    @IncludeUser
    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public String addActorToFilm(){
        request.setAttribute("show", "1");
        if (request.getSession().getAttribute("movie") != null){
            List<Role> roles = Arrays.asList(Role.values());
            request.setAttribute("roles", roles);
            if (request.getSession().getAttribute("actors") == null) {
                List<People> people = peopleRepository.findAll();
                request.setAttribute("actors", people);
                request.getSession().setAttribute("actors", people);
            } else {
                if (((List<People>) request.getSession().getAttribute("actors")).size() == 0){
                    request.setAttribute("show", "0");
                } else {
                    request.setAttribute("actors", request.getSession().getAttribute("actors"));
                }
                request.setAttribute("actorsForFilm", request.getSession().getAttribute("actorsForFilm"));
            }
            return "addActorToFilm";
        } else {
            return "redirect:/add";
        }
    }

    @RequestMapping(value = "/addActor", method = RequestMethod.POST)
    public String addActor(@RequestParam String actor, @RequestParam String role){
        List<People> actors = (List<People>) request.getSession().getAttribute("actors");
        List<People> actorsForFilm = (List<People>) request.getSession().getAttribute("actorsForFilm");
        if (actorsForFilm == null)
            actorsForFilm = new ArrayList<>();
        for (People p: actors) {
            if (p.getId() == Long.valueOf(actor)){
                p.setRole(Role.valueOf(role));
                peopleRepository.saveAndFlush(p);
                actorsForFilm.add(p);
                actors.remove(p);
                break;
            }
        }
        request.getSession().setAttribute("actors", actors);
        request.getSession().setAttribute("actorsForFilm", actorsForFilm);
        return "redirect:/add/actor";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveActors(){
        Movie movie = (Movie) request.getSession().getAttribute("movie");
        movie.setPeoples((List<People>) request.getSession().getAttribute("actorsForFilm"));
        movieRepository.saveAndFlush(movie);
        request.getSession().setAttribute("actors", null);
        request.getSession().setAttribute("actorsForFilm", null);
        return "redirect:/movie/" + movie.getId();
    }
}
