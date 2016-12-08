package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dz.elastic.MovieSearchService;
import ru.dz.entity.Movie;
import ru.dz.repository.AutoCompite;
import ru.dz.repository.AwardRepository;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class IndexController {
    private AutoCompite autoCompite = new AutoCompite();
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AwardRepository awardRepository;
    @PostConstruct
    public void init(){
        //This is auto-importing methods to full fill the database
        //TODO replace to another class
//        movieRepository.save(autoCompite.loadMoviesFromJson());
//        movieRepository.flush();
//        awardRepository.save(autoCompite.loadAwardsFromJson());
//        awardRepository.flush();
//        personRepository.save(autoCompite.loadPeopleFromJson());
//        personRepository.flush();
//        ArrayList<Movie> movies = (ArrayList<Movie>) movieRepository.findAll();
//        for (Movie movy : movies) {
//            movieSearchService.add(movy);
//        }
    }
    private static final String INDEX_TEMPLATE = "index";
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieSearchService movieSearchService;
    @RequestMapping("")
    private String get(){
        return INDEX_TEMPLATE;
    }
    @RequestMapping(value = "loadtest")
    private ResponseEntity<List<Movie>> getall(){
        return ResponseEntity.ok(autoCompite.loadMoviesFromJson());
    }
}
